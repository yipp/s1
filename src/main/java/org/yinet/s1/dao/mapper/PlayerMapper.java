package org.yinet.s1.dao.mapper;

import org.springframework.stereotype.Service;

/**
 * Created by ppdashi on 2017/7/14.
 */
@Service
public interface PlayerMapper {
    PlayerModel selectUser(Integer id);
    PlayerModel selectUserForAccount(String account);
    String selectAll();
    void addUser(PlayerModel model);
    void updateUser(PlayerModel model);
    void deleteUser(Integer id);

}
