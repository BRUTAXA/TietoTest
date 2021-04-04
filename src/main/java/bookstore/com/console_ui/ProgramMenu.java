package bookstore.com.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddBookUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass){
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void print(){
        System.out.println();
        System.out.println("Program menu: ");
        System.out.println("1. Add new book");
        System.out.println();
    }

    public int getMenuNumberFromUser(){
        System.out.println("Enter menu item number to execute: ");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu){
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
