package com.ning.utils;

import com.ning.entity.Lrc;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class LrcUtil {

    public  static List<Lrc> getLrcList(String lrcName){
        List<Lrc> lrcList=new ArrayList<>();
        String prefixTime;
        String content;
        long time;
        try {
            File file=new File(lrcName);
            BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
            String str;
            while((str=bufferedReader.readLine())!=null){
                int index=str.lastIndexOf("]");
                prefixTime=str.substring(1,index);
                content=str.substring(index+1,str.length());
                time=Integer.valueOf(prefixTime);
                Lrc lrc=new Lrc(time,content);
                lrcList.add(lrc);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lrcList;
    }
    public  static List<Lrc> getLrcListFromNet(String lrcUrl){
        List<Lrc> lrcList=new ArrayList<>();
        String prefixTime;
        String content;
        long time;
        URLConnection lrcConnection=null;
        try {
            lrcConnection=new URL(lrcUrl).openConnection();
            lrcConnection.connect();
            InputStream inputStream = lrcConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while((str=bufferedReader.readLine())!=null){
                int index=str.lastIndexOf("]");
                prefixTime=str.substring(1,index);
                content=str.substring(index+1,str.length());
                time=Integer.valueOf(prefixTime);
                Lrc lrc=new Lrc(time,content);
                lrcList.add(lrc);
            }
            bufferedReader.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return lrcList;
    }
    public  static List<Lrc> getLrcLists(String lrcName){
        List<Lrc> lrcList=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String prefixTime;
        String content;
        Date date;
        long time;
        try {
            File file=new File(lrcName);
            BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
            String str;
            while((str=bufferedReader.readLine())!=null){
                int index=str.lastIndexOf("]");
                prefixTime=str.substring(1,index);
                date = sdf.parse("1970-01-01 00:" + prefixTime);
                time=date.getTime();
                content=str.substring(index+1,str.length());
                content=content.replaceAll(" ","");
                Lrc lrc=new Lrc(time,content);
                lrcList.add(lrc);
            }
            bufferedReader.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return lrcList;
    }
    private static int getMusicLength(String songPath){
        AudioInputStream audioInputStream=null;
        AudioFormat audioFormat=null;// 文件格式
        int musicLength=0;
        int len;
        byte[] bytes=new byte[1024];
        try {
            File file=new File(songPath);
            audioInputStream = AudioSystem.getAudioInputStream(file);
            audioFormat = audioInputStream.getFormat();
            // 转换mp3文件编码
            if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        audioFormat.getSampleRate(), 16, audioFormat
                        .getChannels(), audioFormat.getChannels() * 2,
                        audioFormat.getSampleRate(), false);
            }
            //再次重新获得文件输入流
            audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
            while ((len = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
                musicLength += len;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return musicLength;
    }
    public static int getSongDuration(String songPath){
        File file=new File(songPath);
        MP3File mp3File= null;
        int trackLength=0;
        try {
            mp3File = new MP3File(file);
            MP3AudioHeader mp3AudioHeader = mp3File.getMP3AudioHeader();
            trackLength = mp3AudioHeader.getTrackLength()*1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return trackLength;
    }
    public static String getSongUTCDuration(String songPath){
        int songDuration = getSongDuration(songPath);
        songDuration=songDuration/1000;
        int minutes=songDuration/60;
        int seconds=songDuration%60;
        String songUTCDuration="";
        if(seconds<10){
            songUTCDuration=minutes+":0"+seconds;
        }else{
            songUTCDuration=minutes+":"+seconds;
        }
        return songUTCDuration;
    }
    public static void main(String[] args) throws Exception {
        String songUTCDuration =
                getSongUTCDuration("C:\\Users\\ning\\Desktop\\music\\song\\成都-赵雷.mp3");
        System.out.println(songUTCDuration);
        String[] musics={"镜里瘦-花僮"};
//        File file=new File("C:\\Users\\ning\\Desktop\\lrcTest");
//        if(file.isDirectory()){
//            File[] files = file.listFiles();
//            if(files!=null) {
//                musics = new String[files.length];
//                for (int i = 0; i < files.length; i++) {
//                    String filename = files[i].getName();
//                    String songName = filename.substring(0, filename.indexOf("."));
//                    musics[i] = songName;
//                }
//            }
//        }
        String lrcPath="C:\\Users\\ning\\Desktop\\lrcTest\\";
        String songPath="C:\\Users\\ning\\Desktop\\music\\song\\";
        String newLrcPath="C:\\Users\\ning\\Desktop\\music\\lrc\\";
        String[] finalMusics = musics;
        for(int x=0;x<finalMusics.length;x++) {
            int num=x;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File(newLrcPath + finalMusics[num] + ".lrc");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        List<Lrc> lrcList = getLrcLists(lrcPath + finalMusics[num] + ".lrc");
                        int musicLength = getMusicLength(songPath + finalMusics[num] + ".mp3");
                        int songDuration = getSongDuration(songPath + finalMusics[num] + ".mp3");
                        int rate =  musicLength / songDuration;
                        for (Lrc lrc : lrcList) {
                            Long prefixTime = (long) (lrc.getPrefixTime() * rate);
                            String content = lrc.getContent();
                            String lrc1 = "[" + prefixTime + "]" + content + "\r\n";
                            byte[] bytes = lrc1.getBytes();
                            fileOutputStream.write(bytes, 0, bytes.length);
                            fileOutputStream.flush();
                        }
                        String lrc = "[200000000]";
                        byte[] bytes = lrc.getBytes();
                        fileOutputStream.write(bytes, 0, bytes.length);
                        fileOutputStream.flush();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
