package com.example.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class GameRunTimeCCLayer extends CCLayer {
	private MediaPlayer mp; //MediaPlayer����
    private AudioManager am;//AudioManager����
    private int max;//�������
    private int current;//��ǰ����
	private CCSprite sprite_game_bg = null;
	private CCSprite sprite_game_nav = null;
	private CCSprite sprite_game_pause = null;
	private CCSprite sprite_good_perfect = null;
	private CCSprite start_pic_1 = null;
	private CCSprite start_pic_2 = null;
	private CCSprite start_pic_3 = null;
	private String theme_button;
	private String music_name;
	private int theme_button_id;
	private int easy_son_on;
	private CCLabelAtlas lable_game_grade = null;
	// private Formatter grade_temp=new Formatter();
	private int[] game_grade = new int[1];
	private Button_class[] game_button;
	public static int[] tag = new int[4];
	// private CCSprite game_button_2=null;
	// private CCSprite game_button_3=null;
	// private CCSprite game_button_4=null;
	private int speed = 10;
	// private int timer_time=30;
	static ReentrantLock lock = new ReentrantLock();
	private Timer end_game = new Timer();
	private Timer start_game_1 = new Timer();
	private Timer start_game_2 = new Timer();
	private Timer start_game_3 = new Timer();
	private Timer good_perfect_timer=new Timer();
	private int new_end_time;
	private String songName;
	private int[] new_start_time;
	long timeTestStart;//=System.currentTimeMillis();//��¼��ʼʱ��

	long timeTestEnd;//=System.currentTimeMillis();//��¼����ʱ��
	// private Date date=new Date();
	private File_read file_read = new File_read();
	private GamePauseScene pauseScene;
	public void cancel_all(){
		for (int i = 0; i < game_button.length; i++){
			game_button[i].end_time();
		}
		end_game.cancel();
		//mp.stop();
		//mp.release();
	}
	public GameRunTimeCCLayer() {
		this.setIsTouchEnabled(true);
		SharedPreferences preferences=CCDirector.sharedDirector().getActivity().getSharedPreferences("theme", Context.MODE_PRIVATE);
		theme_button=preferences.getString("color", "orange");
		if(theme_button.equals("orange"))
			theme_button_id=1;
		else if(theme_button.equals("red"))
			theme_button_id=2;
		Intent eaIntent=CCDirector.sharedDirector().getActivity().getIntent();
		easy_son_on=eaIntent.getIntExtra("state", 1);
		songName=eaIntent.getStringExtra("songName");
		init();
		// game_started();
		// date=new Date();
		// button_1_start();
		// button_2_start();
		// button_3_start();
		// button_4_start();
		// end_game_start();
		// Intent intent = new Intent(CCDirector.sharedDirector().getActivity(),
		// GameEndActivity.class);
		// CCDirector.sharedDirector().getActivity().startActivity(intent);
		// end_game.schedule(new end_game_class(), 3000);
		for (int i = 0; i < 4; i++) {
			tag[i] = -1;
		}
		if(songName.equals("youngandbeautiful")&&easy_son_on==1)
			file_read.init("music_1.txt");
		else if(songName.equals("youngandbeautiful")&&easy_son_on==2)
			file_read.init("music_2.txt");
		else if(songName.equals("youngandbeautiful")&&easy_son_on==3)
			file_read.init("music_3.txt");
		else if(songName.equals("cktsdxdgl")&&easy_son_on==1)
			file_read.init("cktsdxdgl_1.txt");
		else if(songName.equals("cktsdxdgl")&&easy_son_on==2)
			file_read.init("cktsdxdgl_2.txt");
		else if(songName.equals("cktsdxdgl")&&easy_son_on==3)
			file_read.init("cktsdxdgl_3.txt");
		end_game.schedule(new end_game_class(), file_read.get_end_time() + 3000);
		new_end_time=file_read.get_end_time();
		game_button = new Button_class[file_read.get_button_num()];
		new_start_time=new int[file_read.get_button_num()];
		// System.out.println("!!!!!!!asmbbh   "+file_read.get_button_num());
		// System.out.println("!!!!!!!asmbbh   "+file_read.get_end_time());
		for (int i = 0; i < file_read.get_button_num(); i++) {
			game_button[i] = new Button_class(i, theme_button_id,
					file_read.get_certain_palce(i),
					file_read.get_certain_start_time(i) + 3000, game_grade,
					this);
			new_start_time[i]=file_read.get_certain_palce(i);
		}
		for (int i = 0; i < file_read.get_button_num(); i++)
			game_button[i].start_run();
		start_game_3.schedule(new start_game_class_3(), 0);
		start_game_2.schedule(new start_game_class_2(), 1000);
		start_game_1.schedule(new start_game_class_1(), 2000);
		timeTestStart=System.currentTimeMillis();//��¼��ʼʱ��
	}
	class good_game extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(sprite_good_perfect!=null)
				GameRunTimeCCLayer.this.removeChild(sprite_good_perfect, true);
			sprite_good_perfect=null;

			 sprite_good_perfect =CCSprite.sprite("good.png");

			 sprite_good_perfect.setAnchorPoint(0, 0);

			 sprite_good_perfect.setPosition(450, 300);

			GameRunTimeCCLayer.this.addChild(sprite_good_perfect, 9, 9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameRunTimeCCLayer.this.removeChild(sprite_good_perfect, true);
			sprite_good_perfect=null;
		}

	}
	class perfect_game extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(sprite_good_perfect!=null)
				GameRunTimeCCLayer.this.removeChild(sprite_good_perfect, true);
			sprite_good_perfect=null;

			 sprite_good_perfect =CCSprite.sprite("perfect.png");

			 sprite_good_perfect.setAnchorPoint(0, 0);

			 sprite_good_perfect.setPosition(450, 300);

			GameRunTimeCCLayer.this.addChild(sprite_good_perfect, 9, 9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameRunTimeCCLayer.this.removeChild(sprite_good_perfect, true);
			sprite_good_perfect=null;
		}

	}


	class start_game_class_1 extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(start_pic_1!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_1, true);
			start_pic_1=null;
			if(start_pic_2!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_2, true);
			start_pic_2=null;
			if(start_pic_3!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_3, true);
			start_pic_3=null;

			start_pic_1 = CCSprite.sprite("1.png");

			start_pic_1.setAnchorPoint(0, 0);

			start_pic_1.setPosition(600, 300);

			GameRunTimeCCLayer.this.addChild(start_pic_1, 9, 9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameRunTimeCCLayer.this.removeChild(start_pic_1, true);
			start_pic_1=null;
			//play background music 
			//mp.setDataSource("youngandbeautiful.mp3");//����·��
			//if(music_name=="youngandbeautiful")
			//	mp = MediaPlayer.create(CCDirector.sharedDirector().getActivity(), R.raw.youngandbeautiful);//����·��
			//else if(music_name=="cktsdxdgl")
			if(songName.equals("youngandbeautiful"))
				mp = MediaPlayer.create(CCDirector.sharedDirector().getActivity(), R.raw.youngandbeautiful);//����·��
			else if(songName.equals("cktsdxdgl"))
				mp = MediaPlayer.create(CCDirector.sharedDirector().getActivity(), R.raw.cktsdxdgl);//����·��
	        //mp.prepare();//����

	        am=(AudioManager) CCDirector.sharedDirector().getActivity().getSystemService(Context.AUDIO_SERVICE);
	        max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	        
	        //stepVolume=max/8;
	        mp.start();

		}

	}

	class start_game_class_2 extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(start_pic_1!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_1, true);
			start_pic_1=null;

			if(start_pic_2!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_2, true);
			start_pic_2=null;
			if(start_pic_3!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_3, true);
			start_pic_3=null;

			start_pic_2 = CCSprite.sprite("2.png");

			start_pic_2.setAnchorPoint(0, 0);

			start_pic_2.setPosition(600, 300);

			GameRunTimeCCLayer.this.addChild(start_pic_2, 9, 9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameRunTimeCCLayer.this.removeChild(start_pic_2, true);
			start_pic_2=null;
		}

	}

	class start_game_class_3 extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(start_pic_1!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_1, true);
			start_pic_1=null;
			if(start_pic_2!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_2, true);
			start_pic_2=null;

			if(start_pic_3!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_3, true);
			start_pic_3=null;
			start_pic_3 = CCSprite.sprite("3.png");

			start_pic_3.setAnchorPoint(0, 0);

			start_pic_3.setPosition(600, 300);

			GameRunTimeCCLayer.this.addChild(start_pic_3, 9, 9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameRunTimeCCLayer.this.removeChild(start_pic_3, true);
			start_pic_3=null;
		}

	}
	class start_game_class_1_continue extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(start_pic_1!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_1, true);
			start_pic_1=null;
			if(start_pic_2!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_2, true);
			start_pic_2=null;
			if(start_pic_3!=null)
				GameRunTimeCCLayer.this.removeChild(start_pic_3, true);
			start_pic_3=null;

			start_pic_1 = CCSprite.sprite("1.png");

			start_pic_1.setAnchorPoint(0, 0);

			start_pic_1.setPosition(600, 300);

			GameRunTimeCCLayer.this.addChild(start_pic_1, 9, 9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameRunTimeCCLayer.this.removeChild(start_pic_1, true);
			start_pic_1=null;
			//play background music
	        mp.start();
		}

	}

	class end_game_class extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < game_button.length; i++)
				game_button[i].end_time();
			Intent intent = new Intent(CCDirector.sharedDirector()
					.getActivity(), GameEndActivity.class);
			intent.putExtra("grade_game", game_grade[0]);
			//mp.stop();
			//mp.release();
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
                    Intent.FLAG_ACTIVITY_NEW_TASK);
			CCDirector.sharedDirector().getActivity().startActivity(intent);
			CCDirector.sharedDirector().getActivity().finish();
		}

	}

	private void init() {
		sprite_game_bg = CCSprite.sprite("game_bg.png");
		// CCSprite��Ϸ�����࣬��Ҫ����һ��ͼƬ������Ϸ����

		sprite_game_bg.setAnchorPoint(CGPoint.getZero());
		// ���þ����ê��
		// ê������������Ļ����ʾ��λ�ã�ԭ��Ϊ�������½�Ϊ׼��ê���ֵ���Ա�����ê���Ԫ�ؿ��ߣ�Ϊ�ƶ��ľ���

		sprite_game_bg.setPosition(CGPoint.getZero());
		// ���þ�������꣬����Ļ�����½�Ϊԭ�㣬���ң�����Ϊ������,����OpenGL����ϵ
		this.addChild(sprite_game_bg, 0, 0);
		// thisָ����ǰ���󣬼�MyCCLayer
		// ����ǰͼ�����һ����Ԫ��
		// ����1����Ԫ�ض��󣬲���2����Ԫ����Ҫ�ԣ�����3����Ԫ�صı�ǩ(����ͨ����ǩȡ����Ԫ��)

		sprite_game_nav = CCSprite.sprite("actionbar_bg.png");

		sprite_game_nav.setAnchorPoint(0, 0);

		sprite_game_nav.setPosition(0, 620);

		this.addChild(sprite_game_nav, 1, 1);

		sprite_game_pause = CCSprite.sprite("pause.png");

		sprite_game_pause.setAnchorPoint(0, 0);

		sprite_game_pause.setPosition(40, 630);

		this.addChild(sprite_game_pause, 2, 2);
		lable_game_grade = CCLabelAtlas.label("12", "digits.png", 28, 50, '0');
		// grade_temp.format("%06d", game_grade);
		lable_game_grade.setString("" + game_grade[0]);
		// lable_game_grade.setAnchorPoint(0, 0);
		lable_game_grade.setPosition(370 + 270 + 270, 650);
		this.addChild(lable_game_grade, 8, 8);
		/*
		 * sprite_helloword = CCSprite.sprite("helloworld.png");
		 * sprite_helloword.setAnchorPoint(CGPoint.getZero());
		 * sprite_helloword.setPosition( (this.getContentSize().width -
		 * sprite_helloword .getContentSize().width) / 2,
		 * (this.getContentSize().height - sprite_helloword
		 * .getContentSize().height) / 2); // ������ʾ����Ļ���м�
		 * this.addChild(sprite_helloword, 1, 1); //
		 * sprite_helloword�������Ҫ��Ϊ1����sprite_game_bg����Ҫ��Ϊ0 //
		 * ����sprite_helloword����ʾ��sprite_game_bg���Ϸ� // ����֮������Ҫ��Խ�ߣ�Խ������ʾ���ϲ�
		 */
	}

	// private void game_started(){
	// game_button_1=CCSprite.sprite("touch_button.png");
	// game_button_1.setAnchorPoint(0, 0);
	// game_button_1.setPosition(100, 550);
	// this.addChild(game_button_1, 4, 4);
	//
	// game_button_2=CCSprite.sprite("touch_button.png");
	// game_button_2.setAnchorPoint(0, 0);
	// game_button_2.setPosition(370, 450);
	// this.addChild(game_button_2, 5, 5);
	//
	// game_button_3=CCSprite.sprite("touch_button.png");
	// game_button_3.setAnchorPoint(0, 0);
	// game_button_3.setPosition(370+270, 350);
	// this.addChild(game_button_3, 6, 6);
	//
	// game_button_4=CCSprite.sprite("touch_button.png");
	// game_button_4.setAnchorPoint(0, 0);
	// game_button_4.setPosition(370+270+270, 250);
	// this.addChild(game_button_4, 7, 7);
	// lable_game_grade=CCLabelAtlas.label("12", "digits.png", 28,50, '0');
	// //grade_temp.format("%06d", game_grade);
	// lable_game_grade.setString(""+game_grade);
	// //lable_game_grade.setAnchorPoint(0, 0);
	// lable_game_grade.setPosition(370+270+270, 650);
	// this.addChild(lable_game_grade,8,8);
	//
	// }
	public void pause_game(){
		CCDirector.sharedDirector().pause();
		mp.pause();
		timeTestEnd=System.currentTimeMillis();//��¼����ʱ��
		for (int i = 0; i < game_button.length; i++){
			game_button[i].set_tag(1);
			new_start_time[i]=new_start_time[i]-(int)(timeTestEnd-timeTestStart);
		}
		end_game.cancel();
		this.pauseSchedulerAndActions();
		pauseScene = new GamePauseScene();
		this.addChild(pauseScene, 9999);
	}
	
	public void continue_game(){
		CCDirector.sharedDirector().resume();
		this.removeChild(pauseScene, true);
		pauseScene=null;
		long time_pause=(timeTestEnd-timeTestStart);
//		System.out.println("!!!!   "+time_pause);
		end_game=new Timer();
		if(new_end_time>time_pause)
			end_game.schedule(new end_game_class(), (new_end_time=(new_end_time-(int)time_pause))+3000);
		// System.out.println("!!!!!!!asmbbh   "+file_read.get_button_num());
		//System.out.println("!!!!!!!asmbbh   "+file_read.get_end_time());
		for (int i = 0; i < game_button.length; i++){
			if(tag[0]!=i&&tag[1]!=i&&tag[2]!=i&&tag[3]!=i){
				game_button[i].set_tag(4);
				if(new_start_time[i]-time_pause>0)
					game_button[i].set_sleep((new_start_time[i]=new_start_time[i]-(int)time_pause)+3000);
				else {
					game_button[i].set_sleep(3000);
				}
			}
			else if(tag[0]==i||tag[1]==i||tag[2]==i||tag[3]==i){
				game_button[i].set_tag(3);
			}
		}
		start_game_3.schedule(new start_game_class_3(), 0);
		start_game_2.schedule(new start_game_class_2(), 1000);
		start_game_1.schedule(new start_game_class_1_continue(), 2000);
		timeTestStart=System.currentTimeMillis()+3000;

	}
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {

		CGPoint point = this.convertTouchToNodeSpace(event);
		if (pauseScene==null&&CGRect.containsPoint(sprite_game_pause.getBoundingBox(), point)) {
			CCDirector.sharedDirector().pause();
			mp.pause();
			timeTestEnd=System.currentTimeMillis();//��¼����ʱ��
			for (int i = 0; i < game_button.length; i++){
				game_button[i].set_tag(1);
				new_start_time[i]=new_start_time[i]-(int)(timeTestEnd-timeTestStart);
			}
			end_game.cancel();
			this.pauseSchedulerAndActions();
			pauseScene = new GamePauseScene();
			this.addChild(pauseScene, 9999);
			// System.out.println("pause!!!");
			// CCDirector.sharedDirector().replaceScene(GamePauseScene.scene());
			// CCRenderTexture renderTexture =
			// CCRenderTexture.renderTexture(1280, 720);
			// renderTexture.begin();
			// this.getParent().visit(null);
			// renderTexture.end();

			// CCDirector.sharedDirector().pushScene(GamePauseScene.scene(renderTexture,true));
		}
		if (pauseScene!=null&&CGRect.containsPoint(pauseScene.get_continue().getBoundingBox(),
				point)) {
			CCDirector.sharedDirector().resume();
			this.removeChild(pauseScene, true);
			pauseScene=null;
			long time_pause=(timeTestEnd-timeTestStart);
//			System.out.println("!!!!   "+time_pause);
			end_game=new Timer();
			if(new_end_time>time_pause)
				end_game.schedule(new end_game_class(), (new_end_time=(new_end_time-(int)time_pause))+3000);
			// System.out.println("!!!!!!!asmbbh   "+file_read.get_button_num());
			//System.out.println("!!!!!!!asmbbh   "+file_read.get_end_time());
			for (int i = 0; i < game_button.length; i++){
				if(tag[0]!=i&&tag[1]!=i&&tag[2]!=i&&tag[3]!=i){
					game_button[i].set_tag(4);
					if(new_start_time[i]-time_pause>0)
						game_button[i].set_sleep((new_start_time[i]=new_start_time[i]-(int)time_pause)+3000);
					else {
						game_button[i].set_sleep(3000);
					}
				}
				else if(tag[0]==i||tag[1]==i||tag[2]==i||tag[3]==i){
					game_button[i].set_tag(3);
				}
			}
			start_game_3.schedule(new start_game_class_3(), 0);
			start_game_2.schedule(new start_game_class_2(), 1000);
			start_game_1.schedule(new start_game_class_1_continue(), 2000);
			timeTestStart=System.currentTimeMillis()+3000;
		}
		if (pauseScene!=null&&CGRect.containsPoint(pauseScene.get_back().getBoundingBox(), point)) {
			for (int i = 0; i < game_button.length; i++)
				game_button[i].end_time();
			//mp.stop();
			//mp.release();
			end_game.cancel();
			this.removeChild(pauseScene, true);
			pauseScene=null;
			Intent intent = new Intent(CCDirector.sharedDirector()
					.getActivity(), GameStartActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
                    Intent.FLAG_ACTIVITY_NEW_TASK);
			CCDirector.sharedDirector().getActivity().startActivity(intent);
		}
		if (pauseScene!=null&&CGRect.containsPoint(pauseScene.get_home().getBoundingBox(), point)) {
			for (int i = 0; i < game_button.length; i++)
				game_button[i].end_time();
			end_game.cancel();
			//mp.stop();
			//mp.release();
			this.removeChild(pauseScene, true);
			pauseScene=null;
			Intent intent = new Intent(CCDirector.sharedDirector()
					.getActivity(), MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
                    Intent.FLAG_ACTIVITY_NEW_TASK);
			CCDirector.sharedDirector().getActivity().startActivity(intent);
		}
		for (int i = 0; i < 4; i++) {
			lock.lock();
			if (start_pic_1==null&&start_pic_2==null&&start_pic_3==null&&pauseScene==null&&tag[i] != -1
					&& CGRect.containsPoint(game_button[tag[i]].get_CCS()
							.getBoundingBox(), point)
					&& game_button[tag[i]].get_CCS().getPosition().y < 100
					&& game_button[tag[i]].get_CCS().getPosition().y >= 25) {
				game_grade[0] += 50;
				if(theme_button_id==1)
					game_button[tag[i]].set_CCS(CCSprite.sprite("touched_button.png"));				// grade_temp.format("%06d", game_grade);
				else if(theme_button_id==2)
					game_button[tag[i]].set_CCS(CCSprite.sprite("touched_button_red.png"));	
				lable_game_grade.setString("" + game_grade[0]);
				good_perfect_timer=new Timer();
				good_perfect_timer.schedule(new good_game(), 0);
				tag[i] = -1;
				// System.out.println("grade: "+game_grade);

			}
			else if (start_pic_1==null&&start_pic_2==null&&start_pic_3==null&&pauseScene==null&&tag[i] != -1
					&& CGRect.containsPoint(game_button[tag[i]].get_CCS()
							.getBoundingBox(), point)
					&& game_button[tag[i]].get_CCS().getPosition().y < 25
					&& game_button[tag[i]].get_CCS().getPosition().y >= -50) {
				game_grade[0] += 100;
				if(theme_button_id==1)
					game_button[tag[i]].set_CCS(CCSprite.sprite("touched_button.png"));				// grade_temp.format("%06d", game_grade);
				else if(theme_button_id==2)
					game_button[tag[i]].set_CCS(CCSprite.sprite("touched_button_red.png"));					// grade_temp.format("%06d", game_grade);
				lable_game_grade.setString("" + game_grade[0]);
				good_perfect_timer=new Timer();
				good_perfect_timer.schedule(new perfect_game(), 0);
				tag[i] = -1;
				// System.out.println("grade: "+game_grade);

			}
			lock.unlock();
		}
		// if(CGRect.containsPoint(game_button_2.getBoundingBox(), point)
		// &&game_button_2.getPosition().y<100&&game_button_2.getPosition().y>=0){
		// game_grade+=100;
		// //grade_temp.format("%06d", game_grade);
		// lable_game_grade.setString(""+game_grade);
		// //System.out.println("grade: "+game_grade);
		// }
		// if(CGRect.containsPoint(game_button_3.getBoundingBox(), point)
		// &&game_button_3.getPosition().y<100&&game_button_3.getPosition().y>=0){
		// game_grade+=100;
		// //grade_temp.format("%06d", game_grade);
		// lable_game_grade.setString(""+game_grade);
		// //System.out.println("grade: "+game_grade);
		// }
		// if(CGRect.containsPoint(game_button_4.getBoundingBox(), point)
		// &&game_button_4.getPosition().y<100&&game_button_4.getPosition().y>=0){
		// game_grade+=100;
		// //grade_temp.format("%06d", game_grade);
		// lable_game_grade.setString(""+game_grade);
		// //System.out.println("grade: "+game_grade);
		// }
		return true;
	}
	// public void button_1_start(){
	// if(timer_button_1==null)
	// timer_button_1=new Timer();
	// timer_button_1.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// Date newdate=new Date();
	// if(newdate.getSeconds()-date.getSeconds()>0){
	// game_button_1.setPosition(100,
	// (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
	// //game_button_2.setPosition(370,
	// (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
	// //game_button_3.setPosition(370+270,
	// (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
	// //game_button_4.setPosition(370+270+270,
	// (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
	// }
	// if(game_button_1.getPosition().y==0){
	// try {
	// Thread.sleep(30);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// game_button_1.setPosition(100, (game_button_1.getPosition().y=550));
	// //timer_button_1.cancel();
	// }
	// // if(newdate.getMinutes()-date.getMinutes()==0
	// // &&newdate.getSeconds()-date.getSeconds()>10){
	// // timer_button_1.cancel();
	// // }
	// }
	// }, 0, timer_time);
	// }
	// public void button_2_start(){
	// if(timer_button_2==null)
	// timer_button_2=new Timer();
	// timer_button_2.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// Date newdate=new Date();
	// if(newdate.getSeconds()-date.getSeconds()>0){
	// //game_button_1.setPosition(100,
	// (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
	// game_button_2.setPosition(370,
	// (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
	// //game_button_3.setPosition(370+270,
	// (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
	// //game_button_4.setPosition(370+270+270,
	// (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
	// }
	// if(game_button_2.getPosition().y==0){
	// try {
	// Thread.sleep(30);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// game_button_2.setPosition(370, (game_button_2.getPosition().y=550));
	// //timer_button_2.cancel();
	// }
	// // if(newdate.getMinutes()-date.getMinutes()==0
	// // &&newdate.getSeconds()-date.getSeconds()>10){
	// // timer_button_2=null;
	// // }
	//
	// }
	// }, 0, timer_time);
	// }
	// public void button_3_start(){
	// if(timer_button_3==null)
	// timer_button_3=new Timer();
	// timer_button_3.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// Date newdate=new Date();
	// if(newdate.getSeconds()-date.getSeconds()>0){
	// //game_button_1.setPosition(100,
	// (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
	// //game_button_2.setPosition(370,
	// (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
	// game_button_3.setPosition(370+270,
	// (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
	// //game_button_4.setPosition(370+270+270,
	// (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
	// }
	// if(game_button_3.getPosition().y==0){
	// try {
	// Thread.sleep(30);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// game_button_3.setPosition(370+270, (game_button_3.getPosition().y=550));
	// //timer_button_3.cancel();
	// }
	// // if(newdate.getMinutes()-date.getMinutes()==0
	// // &&newdate.getSeconds()-date.getSeconds()>10){
	// // timer_button_3.cancel();
	// // }
	// }
	// }, 0, timer_time);
	// }
	// public void button_4_start(){
	// if(timer_button_4==null)
	// timer_button_4=new Timer();
	// timer_button_4.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// Date newdate=new Date();
	// if(newdate.getSeconds()-date.getSeconds()>0){
	// //game_button_1.setPosition(100,
	// (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
	// //game_button_2.setPosition(370,
	// (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
	// //game_button_3.setPosition(370+270,
	// (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
	// game_button_4.setPosition(370+270+270,
	// (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
	// }
	// if(game_button_4.getPosition().y==0){
	// try {
	// Thread.sleep(30);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// game_button_4.setPosition(370+270+270,
	// (game_button_4.getPosition().y=550));
	// //timer_button_4.cancel();
	// }
	// // if(newdate.getMinutes()-date.getMinutes()==0
	// // &&newdate.getSeconds()-date.getSeconds()>10){
	// // timer_button_4.cancel();
	// // }
	// }
	// }, 0, timer_time);
	// }
	// public void end_game_start(){
	// end_game.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// Date newdate=new Date();
	// // if(timer_button_1==null&&timer_button_2==null
	// // &&timer_button_3==null&&timer_button_4==null){
	// if(date.getSeconds()-newdate.getSeconds()>10){
	// Intent intent = new Intent(CCDirector.sharedDirector().getActivity(),
	// GameEndActivity.class);
	// CCDirector.sharedDirector().getActivity().startActivity(intent);
	// }
	// }
	// }, 0, timer_time);
	// }
}