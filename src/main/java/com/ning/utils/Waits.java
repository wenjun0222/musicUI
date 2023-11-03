package com.ning.utils;

import com.ning.common_component.LoadingPanel;
import com.ning.ui.main.RightDownPanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Waits {
    private static Runtime runtime=Runtime.getRuntime();

    public static void waitLoad(RightDownPanel rightDownPanel, LoadingPanel loadingPanel)  {
        String line;
        try {
            while(true) {
                Process exec = runtime.exec("ping " + "www.baidu.com");
                InputStream is = exec.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                is.close();
                isr.close();
                br.close();
                if (null != sb && !sb.toString().equals("")) {
                    if (sb.toString().indexOf("TTL") > 0) {
                        // 网络畅通
                        rightDownPanel.changePanel(loadingPanel);
                        break;
                    } else {
                        // 网络不畅通
                        Thread.sleep(1000);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static  void sleep(long millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
