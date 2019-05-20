package com.dfrobot.angelo.blunobasicdemo;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dfrobot.angelo.blunobasicdemo.DBService.DBService;
import com.dfrobot.angelo.blunobasicdemo.Pojo.*;
import com.mysql.jdbc.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;


import static com.dfrobot.angelo.blunobasicdemo.util.Utils.getXor;

public class MainActivity extends BlunoLibrary {
    private Button buttonScan;
    private Button buttonSerialSend;
    private EditText serialSendText;
    private TextView serialReceivedText;
    private TextView serverReceivedText;
    private TextView tvID;
    private TextView tvPrice;
    private int count = 0;
    private TextView tvStart1;
    private TextView tvEnd1;
    private TextView tvDur1;
    private TextView tvState1;
    private TextView tvStart2;
    private TextView tvEnd2;
    private TextView tvDur2;
    private TextView tvState2;
    private TextView tvStart3;
    private TextView tvEnd3;
    private TextView tvDur3;
    private TextView tvState3;
    private TextView tvStart4;
    private TextView tvEnd4;
    private TextView tvDur4;
    private TextView tvState4;
    private final String DEBUG_TAG = "SOCKET";
    private String Msg = "NONE";
    long sec11 = 0;
    long sec12 = 0;
    long sec10 = 0;
    GregorianCalendar cal11 = new GregorianCalendar();
    GregorianCalendar cal12 = new GregorianCalendar();
    long sec21 = 0;
    long sec22 = 0;
    long sec20 = 0;
    GregorianCalendar cal21 = new GregorianCalendar();
    GregorianCalendar cal22 = new GregorianCalendar();
    long sec31 = 0;
    long sec32 = 0;
    long sec30 = 0;
    GregorianCalendar cal31 = new GregorianCalendar();
    GregorianCalendar cal32 = new GregorianCalendar();
    long sec41 = 0;
    long sec42 = 0;
    long sec40 = 0;
    GregorianCalendar cal41 = new GregorianCalendar();
    GregorianCalendar cal42 = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateProcess();                                                        // onCreate Process by BlunoLibrary


        serialBegin(115200);                                                    // set the Uart Baudrate on BLE chip to 115200

        serialReceivedText = (TextView) findViewById(R.id.serialReveicedText);    // initial the EditText of the received data
        serverReceivedText = (TextView) findViewById(R.id.serverReveicedText);    // initial the EditText of the received data
        serialSendText = (EditText) findViewById(R.id.serialSendText);            // initial the EditText of the sending data
        tvStart1 = (TextView) findViewById(R.id.tvStart1);
        tvEnd1 = (TextView) findViewById(R.id.tvEnd1);
        tvDur1 = (TextView) findViewById(R.id.tvDur1);
        tvState1 = (TextView) findViewById(R.id.tvState1);
        tvStart2 = (TextView) findViewById(R.id.tvStart2);
        tvEnd2 = (TextView) findViewById(R.id.tvEnd2);
        tvDur2 = (TextView) findViewById(R.id.tvDur2);
        tvState2 = (TextView) findViewById(R.id.tvState2);
        tvStart3 = (TextView) findViewById(R.id.tvStart3);
        tvEnd3 = (TextView) findViewById(R.id.tvEnd3);
        tvDur3 = (TextView) findViewById(R.id.tvDur3);
        tvState3 = (TextView) findViewById(R.id.tvState3);
        tvStart4 = (TextView) findViewById(R.id.tvStart4);
        tvEnd4 = (TextView) findViewById(R.id.tvEnd4);
        tvDur4 = (TextView) findViewById(R.id.tvDur4);
        tvState4 = (TextView) findViewById(R.id.tvState4);
        tvID = (TextView) findViewById(R.id.tvID);
        tvPrice = (TextView) findViewById(R.id.tvPrice);

//        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		// initial the button for sending the data
//         buttonSerialSend.setOnClickListener(new OnClickListener() {
// 
// 			@Override
// 			public void onClick(View v) {
// 				//  TODO Auto-generated method stub
// 
// 				serialSend(serialSendText.getText().toString());				// send the data to the BLUNO
// 			}
// 		});

        buttonScan = (Button) findViewById(R.id.buttonScan);                    // initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //  TODO Auto-generated method stub

                buttonScanOnClickProcess();                                        // Alert Dialog for selecting the BLE device
            }
        });

    }




    protected void onResume() {
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        onResumeProcess();                                                        // onResume Process by BlunoLibrary
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResultProcess(requestCode, resultCode, data);                    // onActivityResult Process by BlunoLibrary
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();                                                        // onPause Process by BlunoLibrary
    }

    protected void onStop() {
        super.onStop();
        onStopProcess();                                                        // onStop Process by BlunoLibrary
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();                                                        // onDestroy Process by BlunoLibrary
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {// Once connection state changes, this function will be called
        switch (theConnectionState) {                                            // Four connection state
            case isConnected:
                buttonScan.setText("更新成功");
                break;
            case isConnecting:
                buttonScan.setText("连接进行");
                break;
            case isToScan:
                buttonScan.setText("开启管理");
                break;
            case isScanning:
                buttonScan.setText("扫描车位");
                break;
            case isDisconnecting:
                buttonScan.setText("更新失败");
                break;
            default:
                break;
        }
    }



    // 定义值
    ArrayList<Byte> MSG = new ArrayList<>();
    boolean isMSGOK = false;
    char Preamble;
    private boolean flag1 = false;



    @Override
    public void onSerialReceived(byte[] bytes)   {                        // Once connection data received, this function will be called
        // 空字节，不处理返回
        if (bytes == null) {
            return;
        }

        //  判断消息头,#开头接收开始并重置缓存
        Preamble = (char) bytes[0];
        if (Preamble == '#') {
            MSG.clear();
            flag1 = true;
            System.out.println("Stack Clear");
        }
        // 多段数据进行拼接
        for (int i = 0; i < bytes.length; i++) {
            MSG.add(bytes[i]);
        }
        // 检测到消息末尾，一次消息接收成功，标志至1，进行处理"13""10"代表回车换行的ASCII码
        if (MSG.size() >= 2) {
            if (MSG.get(MSG.size() - 2) == 13 && MSG.get(MSG.size() - 1) == 10) {
                isMSGOK = true;
            }
        }

        //消息进行预处理
        if (isMSGOK && MSG.size() >= 8) {
            // 消息转换为byte[]
            byte[] bytesMSG = new byte[MSG.size()];
            for (int i = 0; i < bytesMSG.length; i++) {
                bytesMSG[i] = MSG.get(i);
            }

            //第一次分割，前导码+payload+检验值
            String StringMSG = new String(bytesMSG);
            System.out.println("MSG: " + StringMSG);
            final String[] StringMSG1 = StringMSG.split("\\*");

            //  校验计算，初值赋值差别
            int localXor = 999999;
            int remoteXor = 1;
            if (StringMSG1.length == 3) {
                // 本地校验值
                localXor = getXor(StringMSG1[0].getBytes()) ^ '*' ^ getXor(StringMSG1[1].getBytes()) ^ '*';
                // 远程传递的校验值
                if (StringMSG1[2].length() == 5 || StringMSG1[2].length() == 4) {
                    String str = StringMSG1[2].replaceAll("\r\n", "");
                    String regex = "^[A-Fa-f0-9]+$";
                    if (str.matches(regex)) {
                        remoteXor = Integer.valueOf(str, 16);
                    }
                }
            }
            System.out.println("remoteXor- " + remoteXor + "  loaclXor- " + localXor);

            //  校验结果，16进制及ASCII方法均可识别
            boolean checkXor;
            if (localXor == remoteXor || localXor == Integer.parseInt(String.valueOf(remoteXor), 16)) {
                System.out.println("校验成功");
                checkXor = true;
            } else {
                System.out.println("校验失败");
                Toast.makeText(MainActivity.this, StringMSG1[0].substring(1, 4) + " verification failed!", Toast.LENGTH_LONG).show();
                checkXor = false;
            }

            // 前台展示消息
            serialReceivedText.append(StringMSG);
            ((ScrollView) serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);

            // 数据校验通过，可进行业务处理
            if (checkXor) {
                //  提取消息名称
                String nameMSG="";
                if (StringMSG1[0].length() == 4) {
                    nameMSG = StringMSG1[0].substring(1, 4);
                }

                // PAK业务消息处理
                if (nameMSG.equals("PAK")) {
                    PAK(StringMSG1);
                }

                // TODO: 2019/5/18 可在此添加方法写停车场的初始化

            }
            System.out.println("<信息结束>");
            isMSGOK = false;
        }
        flag1 = false;
    }

    
    /**
     * 车位状态数据处理
     * @param StringMSG1
     */
    private void PAK(String[] StringMSG1){
        // Payload提取
        String[] payload = StringMSG1[1].split(",");

        // 核对payload在业务PAK的完整性
        boolean isPayloadSizeRight = payload.length==6; //是否是6个payload值

        boolean nullInPayload = false; //是否含有空值
        for(String payloadItem:payload){
            if(StringUtils.isNullOrEmpty(payloadItem)){
                nullInPayload=true;
                break;
            }
        }

        // payload验证失败返回
        if((!isPayloadSizeRight)||nullInPayload){
            Toast.makeText(MainActivity.this, "Payload in PAK verification failed!", Toast.LENGTH_LONG).show();
            return;
        }

        // parkingMessage对象并赋值
        final ParkingMessage  parkingMessage = new ParkingMessage();
        parkingMessage.setZoneId(Integer.valueOf(payload[0]));
        parkingMessage.setParkinglotId(Integer.valueOf(payload[1]));
        parkingMessage.setParkingSpaceId(Integer.valueOf(payload[2]));
        parkingMessage.setUserId(Integer.valueOf(payload[3]));
        String string = payload[4];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date msgTime = sdf.parse(string);
            parkingMessage.setMsgTime(msgTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parkingMessage.setStatus(Integer.valueOf(payload[5]));

        // 数据库操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 查车位和车场表
                ParkingSpace parkingSpace = DBService.getDbService().getParkingspaceWithSpaceIdAndLotId(parkingMessage.getParkingSpaceId(),parkingMessage.getParkinglotId());
                Parkinglot parkinglot = DBService.getDbService().getParkinglot(parkingMessage.getParkinglotId(),parkingMessage.getZoneId());
                
                // 判断是否车位是新建的，不是新建的则插入
                if(parkingSpace.getId()==null){
                    // 如果查询为空，则新插入数据
                    // 插入space表
                    parkingSpace.setStatus(parkingMessage.getStatus());
                    parkingSpace.setUserId(parkingMessage.getUserId());
                    parkingSpace.setParkingspaceId(parkingMessage.getParkingSpaceId());
                    parkingSpace.setParkinglotId(parkingMessage.getParkinglotId());
                    parkingSpace.setStartTime(parkingMessage.getMsgTime());
                    DBService.getDbService().insertParkingSpace(parkingSpace);

                    // 更新车场表
                    if(parkinglot.getSpaceAvailable()!=null){
                        int parkingspaceAvailable = parkinglot.getSpaceAvailable();
                        parkingspaceAvailable--;
                        parkinglot.setSpaceAvailable(parkingspaceAvailable);
                        DBService.getDbService().updateParkinglot(parkinglot);
                    }
                }else {
                    // 车位非新建，更新或删除操作

                    // indexSpace存之前车位的状态
                    int indexSpace = parkingSpace.getStatus();
                    parkingSpace.setStatus(parkingMessage.getStatus());
                    parkingSpace.setUserId(parkingMessage.getUserId());

                    // 状态1到来，进来停车
                    if(parkingMessage.getStatus().intValue()==1){
                        // 更新车位表
                        parkingSpace.setStartTime(parkingMessage.getMsgTime());
                        DBService.getDbService().updateParkingSpace(parkingSpace);

                        // 更新车场表，车位状态变化时才更新
                        if(parkingMessage.getStatus()!=indexSpace){

                            // 需要更新车库数量
                            if(parkinglot.getSpaceAvailable()!=null){
                                int parkingspaceAvailable = parkinglot.getSpaceAvailable();
                                parkingspaceAvailable--;
                                parkinglot.setSpaceAvailable(parkingspaceAvailable);
                                DBService.getDbService().updateParkinglot(parkinglot);
                            }
                        }
                    }

                    // 状态0到来，离开车位
                    if(parkingMessage.getStatus().intValue()==0){
                        // 更新车位表
                        parkingSpace.setEndTime(parkingMessage.getMsgTime());
                        DBService.getDbService().updateParkingSpace(parkingSpace);

                        // 更新车场表，车位状态变化时才更新
                        if(parkingMessage.getStatus()!=indexSpace){
                            if(parkinglot.getSpaceAvailable()!=null){
                                int parkingspaceAvailable = parkinglot.getSpaceAvailable();
                                parkingspaceAvailable++;
                                parkinglot.setSpaceAvailable(parkingspaceAvailable);
                                DBService.getDbService().updateParkinglot(parkinglot);
                            }
                        }
                    }


                }
            }
        }).start();
    }


    // TODO: 2019/5/20 添加车位数据初始化内容

}