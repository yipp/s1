package org.yinet.s1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.yinet.s1.dao.po.basic.Player;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ApplicationContext ctx = null;
    private static JdbcTemplate jdbcTemplate;
    public static void main( String[] args ) throws SQLException {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        testUpdate();
        //queryForObject();

    }
    public static void testUpdate(){
        String spl = "UPDATE user set basicInfo = ? WHERE id = ?";
        String sql = "insert into user (basicInfo,resources) values (?,?)";
        jdbcTemplate.update(sql,"王八","xxx","vvv");
    }
    public static void queryForObject(){
        String sql = "SELECT id,basicInfo,resources FROM user WHERE id=?";
        RowMapper<Player> rowMapper = new BeanPropertyRowMapper(Player.class);
        Player player = jdbcTemplate.queryForObject(sql,rowMapper,1);
        System.out.println(player);
    }
}
