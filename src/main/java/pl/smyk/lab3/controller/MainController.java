package pl.smyk.lab3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.smyk.lab3.model.Code;
import pl.smyk.lab3.utils.HibernateUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainController {
    private final String fileName = "C:\\Users\\kamil.smyk\\Pulpit\\kody.csv";
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Session session = null;
        String record = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            while ((record = reader.readLine()) != null) {
                String[] data = record.split(";");
                Code code = new Code();
                code.setPostCode(data[0]);
                code.setAdress(data[1]);
                code.setPlace(data[2]);
                code.setVoivoship(data[3]);
                code.setCounty(data[4]);
                session.save(code);
            }
            transaction.commit();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}