package sax;

import model.User;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class UsersHandler extends DefaultHandler {
    private List<User> users;
    private User user;
    boolean isLogin = false;
    boolean isPassword = false;

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("User")) {
            String id = attributes.getValue("id");
            user = new User();
            user.setId(Integer.parseInt(id));
            if (users == null) {
                users = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("login")) {
            isLogin = true;
        } else if (qName.equalsIgnoreCase("password")) {
            isPassword = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("User")) {
            users.add(user);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {

        if (isLogin) {
            user.setLogin(new String(characters, start, length));
            isLogin = false;
        } else if (isPassword) {
            user.setPassword(new String(characters, start, length));
            isPassword = false;
        }
    }
}
