import base.DbTestBase;
import com.mycurdpro.system.model.SysVisitLog;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class ExportExcelTest {

    private  final static Logger LOG = LoggerFactory.getLogger(ExportExcelTest.class);
    @Test
    public void test(){
        DbTestBase.dbInit();
        LOG.info(new DateTime(new Date()).toString("yyyy-MM-dd HH:mm:ss.S"));
        List<SysVisitLog> sysVisitLogs = SysVisitLog.dao.find("select * from sys_visit_log");
        LOG.info("data size:{}",sysVisitLogs.size());
        LOG.info(new DateTime(new Date()).toString("yyyy-MM-dd HH:mm:ss.S"));
    }
}
