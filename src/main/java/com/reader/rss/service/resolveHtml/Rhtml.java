package com.reader.rss.service.resolveHtml;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class Rhtml implements IRhtml{
    @Autowired
    WebDriver driver;
    @Override
    public Set<String> selfResolve(String url) {
            Set<String> set=new HashSet<>();
            driver.get(url);
            for (int i = 0; i < 5; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            List<WebElement> elements = driver.findElements(By.tagName("a"));
            String classname;
        for(WebElement element:elements){
            classname=element.getAttribute("class");
            if(classname!=null) {
                System.out.println(classname);
                set.add(classname);
            }
        }
        return set;
    }
}
