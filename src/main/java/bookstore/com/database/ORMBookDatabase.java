package bookstore.com.database;

import bookstore.com.core.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*
//@Component
//@Transactional
public class ORMBookDatabase implements BookDatabase{

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }
}

 */
