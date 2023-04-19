package pl.smyk.lab3.dao;

import javafx.scene.control.Alert;
import org.hibernate.*;
import pl.smyk.lab3.model.Code;
import pl.smyk.lab3.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeDAO {
    private SessionFactory sessionFactory;

    public CodeDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void loadData() {
        Session session = null;
        String record = null;
        if (!getAll().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Data can not be entered into database because already has data entered!");
            alert.show();
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\kamil.smyk\\Pulpit\\kody.csv"));
                reader.readLine();
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
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
                session.getTransaction().commit();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<Code> getAll() {
        try {
            Session session = sessionFactory.openSession();
            return session.createQuery("from Code", Code.class).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Code> getByCriteria(String postCode, String address, String place, String voivoship, String county) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Code e where e.postCode LIKE :postCode AND e.adress LIKE :adress AND e.place LIKE :place AND e.voivoship LIKE :voivoship AND e.county LIKE :county";
        Query<Code> query = session.createQuery(hql, Code.class);
        query.setParameter("postCode", "%" + postCode + "%");
        query.setParameter("adress", "%" + address + "%");
        query.setParameter("place", "%" + place + "%");
        query.setParameter("voivoship", "%" + voivoship + "%");
        query.setParameter("county", "%" + county + "%");

        List<Code> result = query.getResultList();
        return result;
    }

    public List<Code>getByPlace(String place) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Code e where e.place LIKE :place";
        Query<Code> query = session.createQuery(hql, Code.class);
        query.setParameter("place","%" + place + "%");

        List<Code> result = query.getResultList();
        return result;
    }
}
