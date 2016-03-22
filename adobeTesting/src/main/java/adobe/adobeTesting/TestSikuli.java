package adobe.adobeTesting;

import org.sikuli.script.*;

public class TestSikuli {

        public static void main(String[] args) throws InterruptedException {
                Screen s = new Screen();
                try{
                        s.click("imgs/1456463407918.png");
                        s.wait("imgs/1456463407919.png");
                        s.type("Calculator"+Key.ENTER);
                        Thread.sleep(100);
                       // s.wait("1456463582198.png");
                        s.click("imgs/1456463796338.png");
                        s.click("imgs/1456463806640.png");
                        s.click("imgs/1456463813993.png");
                        s.click("imgs/1456463820934.png");
                       
                       
                   
                }
                catch(FindFailed e){
                        e.printStackTrace();
                }
        }

}