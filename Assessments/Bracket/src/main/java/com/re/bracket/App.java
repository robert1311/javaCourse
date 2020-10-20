/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket;

import com.re.bracket.controller.TournamentController;
import com.re.bracket.dao.TeamAuditDao;
import com.re.bracket.dao.TeamAuditDaoFileImpl;
import com.re.bracket.dao.TeamDao;
import com.re.bracket.dao.TeamDaoFileImpl;
import com.re.bracket.dao.TournamentAuditDao;
import com.re.bracket.dao.TournamentAuditDaoFileImpl;
import com.re.bracket.dao.TournamentConfigurationDao;
import com.re.bracket.dao.TournamentConfigurationDaoFileImpl;
import com.re.bracket.dao.TournamentDao;
import com.re.bracket.dao.TournamentDaoFileImpl;
import com.re.bracket.dao.UserAuditDao;
import com.re.bracket.dao.UserAuditDaoFileImpl;
import com.re.bracket.dao.UserDao;
import com.re.bracket.dao.UserDaoFileImpl;
import com.re.bracket.service.TeamServiceLayer;
import com.re.bracket.service.TeamServiceLayerImpl;
import com.re.bracket.service.TournamentServiceLayer;
import com.re.bracket.service.TournamentServiceLayerImpl;
import com.re.bracket.service.UserServiceLayer;
import com.re.bracket.service.UserServiceLayerImpl;
import com.re.bracket.ui.UserIO;
import com.re.bracket.ui.UserIOConsoleImpl;
import com.re.bracket.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rober
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIO = new UserIOConsoleImpl();
//        View view = new View(myIO);
//        TournamentDao tDao = new TournamentDaoFileImpl();
//        TeamDao tmDao = new TeamDaoFileImpl();
//        UserDao uDao = new UserDaoFileImpl();
//        TournamentAuditDao tAudit = new TournamentAuditDaoFileImpl();
//        TeamAuditDao tmAudit = new TeamAuditDaoFileImpl();
//        UserAuditDao uAudit = new UserAuditDaoFileImpl();
//        TournamentConfigurationDao config = 
//                new TournamentConfigurationDaoFileImpl();
//        TournamentServiceLayer tService = new TournamentServiceLayerImpl(tDao, 
//                tAudit, config);
//        TeamServiceLayer tmService = new TeamServiceLayerImpl(tmDao, tmAudit, 
//                config);
//        UserServiceLayer uService = new UserServiceLayerImpl(uDao, uAudit);
//
//        TournamentController controller = new TournamentController(tService, 
//                tmService, uService, view);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        TournamentController controller = 
                ctx.getBean("controller", TournamentController.class);
        controller.run();
    }
}
