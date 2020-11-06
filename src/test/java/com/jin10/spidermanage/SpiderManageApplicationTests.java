package com.jin10.spidermanage;

import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpiderManageApplicationTests {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    @Test
    void contextLoads() throws IOException, InterruptedException {
//        JSONObject jsonObject = XxlJobUtil.deleteJob(adminAddresses, 15);
//        System.out.println(jsonObject);

//        Http.requestTest("http://baidu.com/");


        XxlJobUtil.executorList(adminAddresses);
    }

    @Test
    void printTest() {
//        String [][] printTest = new String[17][17];
//        for (int x = 0; x < 17; x++ ) {
//            for (int y = 0; y < 17; y++ ) {
//                if (x == 0 && y > 0) {
//                    if (y > 10) {
//                        printTest[x][y] = " " + (char)(87 + (y - 1)) + " ";
//                        continue;
//                    }else {
//                        printTest[x][y] = " " + (y-1) + " ";
//                    }
//                } else if (x > 0 && y == 0){
//                    if (x > 10) {
//                        printTest[x][y] = String.valueOf((char)(87 + (x - 1)));
//                        continue;
//                    }else {
//                        printTest[x][y] = String.valueOf(x - 1);
//                    }
//                } else if (x == 0 && y == 0) {
//                    printTest[x][y] = " ";
//                } else {
//                    printTest[x][y] = " + ";
//                }
//            }
//        }
//
//        for (int i = 0; i < printTest.length; i++) {
//            for (int j = 0; j < printTest[i].length; j++) {
//                System.out.print(printTest[i][j]);
//            }
//            System.out.println();
//        }
//    }

        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 17; y++) {
                if (x == 0 && y == 0) {
                    System.out.print(" ");
                }else if (x == 0 && y > 0) {
                    if (y < 11) {
                        System.out.print(" " + (y-1) + " ");
                    } else {
                        System.out.print(" " + (char)(87 + (y - 1)) + " ");
                    }
                } else if (x > 0 && y == 0) {
                    System.out.println();
                    if (x < 11) {
                        System.out.print(x-1);
                    } else {
                        System.out.print((char)(87 + (x - 1)));
                    }
                } else {
                    System.out.print(" + ");
                }
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
    }
}
