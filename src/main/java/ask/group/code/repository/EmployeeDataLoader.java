package ask.group.code.repository;

import ask.group.code.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmployeeDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private volatile static boolean dataHasBeenLoaded = false;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private EmployeeRepository empRepository;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(! dataHasBeenLoaded) {
            synchronized (EmployeeDataLoader.class) {
                if(! dataHasBeenLoaded) {
                    dataHasBeenLoaded = true;
                    loadData();
                }
            }
        }
    }

    private void loadData() {
        FileReader fis = null;
        BufferedReader bis = null;
        try {
            String dataFile = env.getProperty("employee.data.file", "data/Employee.csv");
            fis = new FileReader(dataFile);
            bis = new BufferedReader(fis);
            String oneLine = bis.readLine();
            while((oneLine = bis.readLine()) != null) {
                String [] strAry = oneLine.split(",");
                if(strAry.length == 5) {
                    Employee e = new Employee();
                    e.setFirstName(strAry[0]);
                    e.setMiddleInitial(strAry[1]);
                    e.setLastName(strAry[2]);
                    e.setStatus(Employee.Active);
                    e.setDateOfBirth(cleanDateData(strAry[3]));
                    e.setDateOfEmployment(cleanDateData(strAry[4]));
                    empRepository.save(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Date cleanDateData(String dateData) {
        Date retDate = null;
        if(dateData != null) {
            dateData = dateData.trim();
            if (dateData.startsWith("'")) {
                dateData = dateData.substring(1);
            }
            try {
                retDate = sdf.parse(dateData);
            } catch (Exception e) {
                retDate = null;
            }
        }
        return retDate;
    }
}
