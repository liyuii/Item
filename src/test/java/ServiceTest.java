import com.test.MainApplication;
import com.test.dao.TableDao;
import com.test.entity.Form;
import com.test.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ServiceTest {

    @Autowired
    TableService tableService;

//    @Test
//    public void myTest(){
////        System.out.println("test");
//        List<Form> forms = tableService.tablePage();
//        System.out.println(forms.toString());
//    }
}
