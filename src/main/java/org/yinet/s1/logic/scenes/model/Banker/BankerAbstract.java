package org.yinet.s1.logic.scenes.model.Banker;

import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.logic.scenes.data.Banker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppdashi on 2017/7/16.
 */
public abstract class BankerAbstract {
   public Banker bankers = null;
   public List<Banker> baner = new ArrayList<>();
    /**
     * 上装列表
     */
    public List<Integer> bankerList = new ArrayList<>();
    public final static int SYSTEM_BANKER_ID = -77888;//系统庄家id

    /**
     * 上庄
     */
    public void bankerUp() {
        //如果当前没有庄家
        if (baner.isEmpty()) {
            if (!bankerList.isEmpty()) {
                int bankerId = bankerList.get(0);//添加庄家
                Player player = UserCache.playerId.get(bankerId);
                bankers = new Banker(player.getId(),0,player.getResources().getGold());
                baner.add(bankers);
                bankerList.remove(0);//从上庄列表中移除
            } else {
                //添加庄家
                bankers = new Banker(SYSTEM_BANKER_ID,0,778899002233L);
                baner.add(bankers);
            }
        }
    }

    /**
     * 下庄
     * @param bankerDown：是否自愿下庄
     */
    public void bankerDown(boolean bankerDown) {
        //同一个玩家连续在庄10把或者玩家主动下庄
        if (bankers.getBankerNumber() >= 10 || bankerDown) {
           baner.clear();
        }
    }
}
