/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jayang
 */
@Service
public class LogService {

    ///实例化日志类
  ///  private static Logger logger = LogManager.getLogger(LogService.class);
   private static Log log = LogFactory.getLog(LogService.class);
//    public static Log getLog(Class type) {
//       /// log = LogFactory.getLog(type);
//        return log;
//    }
//    public static Log getLog() {
//        log = LogFactory.getLog(LogService.class);
//        return log;
//    }
    public void add(Log log) throws ClassNotFoundException, NoSuchFieldException {

    }

    public static void main(String[] args) {
      
        log.error("ERROR");
        log.debug("DEBUG");
        log.warn("WARN");
        log.info("INFO");
        log.trace("TRACE");


//      logger.debug("debug");
//logger.info("info");
//logger.warn("warn");
//logger.error("error");
       System.out.println(log.getClass());
         /// System.out.println(logger.getClass());
    }
}
