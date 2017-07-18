package org.yinet.s1.dao.manager;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.dao.mapper.PlayerMapper;
import org.yinet.s1.dao.mapper.PlayerModel;
import org.yinet.s1.dao.po.basic.BasicInfo;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.dao.po.basic.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppdashi on 2017/7/14.
 */
@Service
public class SerializerPlayerModel {
    @Autowired
    private PlayerMapper playerMapper;
    /**查找角色信息*/
    public Player getPlayer(int id){
        PlayerModel playerModel = playerMapper.selectUser(id);
        BasicInfo basicInfo = JSON.parseObject(playerModel.getBasicInfo(),BasicInfo.class);
        Resources resources = JSON.parseObject(playerModel.getResources(),Resources.class);
        List<Integer> frients = JSON.parseArray(playerModel.getFrients(),Integer.class);
        String account = playerModel.getAccount();
        return new Player(playerModel.getId(),basicInfo,resources,frients,account);
    }
    public Player getPlayer(String account){
        PlayerModel playerModel = playerMapper.selectUserForAccount(account);
        BasicInfo basicInfo = JSON.parseObject(playerModel.getBasicInfo(),BasicInfo.class);
        Resources resources = JSON.parseObject(playerModel.getResources(),Resources.class);
        List<Integer> frients = JSON.parseArray(playerModel.getFrients(),Integer.class);
        String sqlAccount = playerModel.getAccount();
        return new Player(playerModel.getId(),basicInfo,resources,frients,sqlAccount);
    }
    /**添加角色信息*/
    public void insertPlayer(Player player){
        String basicInfo = JSON.toJSONString(player.getBasicInfo());
        String resources = JSON.toJSONString(player.getResources());
        if(player.getFrients() == null)
            player.setFrients(new ArrayList<>());
        String frients = JSON.toJSONString(player.getFrients(),true);
        String account = player.getAccount();
        PlayerModel model = new PlayerModel(player.getId(),basicInfo,resources,frients,account);
        playerMapper.addUser(model);
    }
    /**更新角色信息*/
    public void updatePlayer(Player player){
        String basicInfo = JSON.toJSONString(player.getBasicInfo());
        String resources = JSON.toJSONString(player.getResources());
        String frients  = JSON.toJSONString(player.getFrients(),true);
        String account = player.getAccount();
        PlayerModel model = new PlayerModel(player.getId(),basicInfo,resources,frients,account);
        playerMapper.updateUser(model);
    }
    public void delectPlayer(Integer id){
        playerMapper.deleteUser(id);
    }
}
