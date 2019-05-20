package com.dfrobot.angelo.blunobasicdemo.DBService;

import com.dfrobot.angelo.blunobasicdemo.Pojo.ParkingSpace;
import com.dfrobot.angelo.blunobasicdemo.Pojo.Parkinglot;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class DBService {
 
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合
 
    //DBService 对象
    public static DBService dbService=null;

    /**
     * 构造方法 私有化
     */
    private DBService(){
    }
 
    /**
     * 获取MySQL数据库单例类对象
     */
    public static DBService getDbService(){
        if(dbService==null){
            dbService=new DBService();
        }
        return dbService;
    }


    /**
     * getParkinglot
     * @param parkinglotId
     * @param zoneId
     * @return
     */
    public Parkinglot getParkinglot(int parkinglotId,int zoneId){
        //结果存放集合
        Parkinglot parkinglot = new Parkinglot();
        //MySQL 语句
        String sql="select * from parkinglot where id ="+parkinglotId+" and zone_id = "+zoneId;
        //获取链接数据库对象
        conn= DBInitialize.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            parkinglot.setId(rs.getInt("id"));
                            parkinglot.setZoneId(rs.getInt("zone_id"));
                            parkinglot.setFee(rs.getDouble("fee"));
                            parkinglot.setSpaceTotal(rs.getInt("parkingspace_total"));
                            parkinglot.setSpaceAvailable(rs.getInt("parkingspace_available"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInitialize.closeAll(conn,ps,rs);//关闭相关操作
        return parkinglot;
    }

    /**
     * updateParkinglot
     * @param
     * @return
     */
    public void updateParkinglot(Parkinglot parkinglot){
        //MySQL 语句
        String sql = "update parkinglot set parkingspace_available=?, gmt_modified=? where id="+parkinglot.getId()+" and zone_id="+parkinglot.getZoneId();
        //获取链接数据库对象
        conn= DBInitialize.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    ps.setInt(1,parkinglot.getSpaceAvailable());
                    Date date = new Date();
                    ps.setTimestamp(2,new java.sql.Timestamp(date.getTime()));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInitialize.closeAll(conn,ps,rs);//关闭相关操作
    }


    /**
     * getParkingspaceWithSpaceIdAndLotId
     * @param
     * @return
     */
    public ParkingSpace getParkingspaceWithSpaceIdAndLotId(int parkingspaceId,int parkinglotId){
        ParkingSpace parkingSpace = new ParkingSpace();
        //MySQL 语句
        String sql="select * from parkingspace where  parkingspace_id="+parkingspaceId+" and " +"parkinglot_id="+parkinglotId;
        //获取链接数据库对象
        conn= DBInitialize.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            parkingSpace.setId(rs.getInt("id"));
                            parkingSpace.setParkingspaceId(rs.getInt("parkingspace_id"));
                            parkingSpace.setParkinglotId(rs.getInt("parkinglot_id"));
                            parkingSpace.setStatus(rs.getInt("status"));
                            parkingSpace.setUserId(rs.getInt("user_id"));
                            parkingSpace.setStartTime(rs.getTimestamp("start_time"));
                            parkingSpace.setEndTime(rs.getTimestamp("end_time"));
                            parkingSpace.setCreateTime(rs.getTimestamp("gmt_create"));
                            parkingSpace.setModifiedTime(rs.getTimestamp("gmt_modified"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInitialize.closeAll(conn,ps,rs);
        return parkingSpace;
    }




    /**
     * insertParkingSpace
     * @param
     * @return
     */
    public void insertParkingSpace(ParkingSpace  parkingSpace){
        //MySQL 语句
        String sql="insert into parkingspace(parkingspace_id,parkinglot_id,status,user_id,start_time,end_time,gmt_create,gmt_modified) values(?,?,?,?,?,?,?,?)";
        //获取链接数据库对象
        conn= DBInitialize.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    ps.setInt(1,parkingSpace.getParkingspaceId());
                    ps.setInt(2,parkingSpace.getParkinglotId());
                    ps.setInt(3,parkingSpace.getStatus());
                    ps.setInt(4,parkingSpace.getUserId());
                    ps.setTimestamp(5,new java.sql.Timestamp(parkingSpace.getStartTime().getTime()));
                    ps.setTimestamp(6,new java.sql.Timestamp(parkingSpace.getStartTime().getTime()));
                    Date date = new Date();
                    ps.setTimestamp(7,new java.sql.Timestamp(date.getTime()));
                    ps.setTimestamp(8,new java.sql.Timestamp(date.getTime()));
                    ps.executeUpdate();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInitialize.closeAll(conn,ps,rs);
    }

    /**
     * updateParkingSpace
     * @param parkingSpace
     */
    public void updateParkingSpace(ParkingSpace  parkingSpace){
        //MySQL 语句
        String sql = "update parkingspace set status=?, user_id=? ,start_time=?,end_time=?,gmt_modified=?  where parkingspace_id="+parkingSpace.getParkingspaceId()+" and parkinglot_id="+parkingSpace.getParkinglotId();
        //获取链接数据库对象
        conn= DBInitialize.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){

                    ps.setInt(1,parkingSpace.getStatus());
                    ps.setInt(2,parkingSpace.getUserId());
                    ps.setTimestamp(3,new java.sql.Timestamp(parkingSpace.getStartTime().getTime()));
                    ps.setTimestamp(4,new java.sql.Timestamp(parkingSpace.getEndTime().getTime()));

                    Date date = new Date();
                    ps.setTimestamp(5,new java.sql.Timestamp(date.getTime()));

                    ps.executeUpdate();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInitialize.closeAll(conn,ps,rs);
    }

}
