package com.example.application.views.preislist;

import com.example.application.data.TravelOrder;
import com.example.application.data.TravelOrderException;
import com.example.application.service.TravelOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Preis List")
@Route("preis")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PreisListView extends VerticalLayout {

    private final Button buttonAdd = new Button("Add order");
    private final Button buttonRemoveAll = new Button("Remove all orders");
    private final Button buttonAdd10 = new Button("Add 10 orders");
    private final Button buttonAddWrong = new Button("Add WRONG order");

    private final Grid<TravelOrder> grid = new Grid<>(TravelOrder.class, false);
    private final TravelOrderService travelOrderService;

    public PreisListView(@Autowired TravelOrderService travelOrderService) {
        this.travelOrderService = travelOrderService;

        setSpacing(true);
        setSizeFull();

        buttonAdd.addClickListener(event -> addOrder());
        buttonRemoveAll.addClickListener(event -> removeAllOrders());
        buttonAdd10.addClickListener(event -> add10Orders());
        buttonAddWrong.addClickListener(event -> addWrongOrder());

        HorizontalLayout buttons = new HorizontalLayout(
                buttonAdd,
                buttonRemoveAll,
                buttonAdd10,
                buttonAddWrong
        );
        buttons.setSpacing(true);

        setupGridColumns();

        grid.setSizeFull();

        if (travelOrderService.findAll().isEmpty()) {
            travelOrderService.fillTestData(10);
        }

        add(buttons, grid);
        reload();
    }

    private void setupGridColumns() {
        grid.addColumn(order -> order.getOrderId())
                .setHeader("Order ID")
                .setSortable(true);

        grid.addColumn(order -> order.getOrderDate())
                .setHeader("Order Date")
                .setSortable(true);

        grid.addColumn(order -> order.getDestination())
                .setHeader("Destination")
                .setSortable(true);

        grid.addColumn(order -> order.getTravelClass())
                .setHeader("Class")
                .setSortable(true);

        grid.addColumn(order -> order.getPrice())
                .setHeader("Price")
                .setSortable(true);

        grid.addColumn(order -> order.getPersons())
                .setHeader("Persons")
                .setSortable(true);

        grid.addComponentColumn(order -> {
                    Checkbox checkbox = new Checkbox(Boolean.TRUE.equals(order.getInsurance()));
                    checkbox.setReadOnly(true);
                    return checkbox;
                })
                .setHeader("Insurance")
                .setSortable(true)
                .setComparator(order -> Boolean.TRUE.equals(order.getInsurance()));

        grid.addComponentColumn(order -> new Button("Delete", event -> removeSelected(order.getOrderId())))
                .setHeader("Delete")
                .setSortable(false);

        grid.addComponentColumn(order -> new Button("One more", event -> oneMore(order.getOrderId())))
                .setHeader("One more")
                .setSortable(false);
    }

    private void addOrder() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("New Travel Order");

        DatePicker orderDate = new DatePicker("Order date");

        TextField destination = new TextField("Destination");

        ComboBox<String> travelClass = new ComboBox<>("Travel class");
        travelClass.setItems(
                "Economy",
                "Premium Economy",
                "Business Class",
                "First Class"
        );

        NumberField price = new NumberField("Price");
        IntegerField persons = new IntegerField("Persons");
        Checkbox insurance = new Checkbox("Insurance");

        BeanValidationBinder<TravelOrder> binder =
                new BeanValidationBinder<>(TravelOrder.class);

        binder.forField(orderDate)
                .bind("orderDate");

        binder.forField(destination)
                .bind("destination");

        binder.forField(travelClass)
                .bind("travelClass");

        binder.forField(price)
                .bind("price");

        binder.forField(persons)
                .bind("persons");

        binder.forField(insurance)
                .bind("insurance");

        TravelOrder order = new TravelOrder();
        order.setOrderId();

        binder.setBean(order);

        VerticalLayout formLayout = new VerticalLayout(
                orderDate,
                destination,
                travelClass,
                price,
                persons,
                insurance
        );

        Button saveButton = new Button("OK", event -> {
            try {
                if (binder.validate().isOk()) {
                    travelOrderService.addOrder(order);
                    reload();
                    dialog.close();
                    Notification.show("Travel order saved");
                } else {
                    Notification.show("Check your input");
                }
            } catch (TravelOrderException e) {
                Notification.show(e.getMessage(), 4000, Notification.Position.MIDDLE);
            }
        });

        Button cancelButton = new Button("Cancel", event -> dialog.close());

        dialog.add(formLayout);
        dialog.getFooter().add(cancelButton, saveButton);

        dialog.open();
    }

    private void removeAllOrders() {
        travelOrderService.removeAll();
        buttonRemoveAll.setEnabled(false);
        reload();
    }

    private void add10Orders() {
        travelOrderService.fillTestData(10);
        buttonRemoveAll.setEnabled(true);
        reload();
    }

    private void addWrongOrder() {
        try {
            travelOrderService.addWrongOrder();
            reload();
        } catch (TravelOrderException e) {
            Notification.show(e.getMessage(), 4000, Notification.Position.MIDDLE);
        }
    }

    private void removeSelected(Long orderId) {
        try {
            travelOrderService.removeTravelOrder(orderId);
            reload();
        } catch (TravelOrderException e) {
            Notification.show(e.getMessage(), 4000, Notification.Position.MIDDLE);
        }
    }

    private void oneMore(Long orderId) {
        try {
            travelOrderService.oneMore(orderId);
            reload();
        } catch (TravelOrderException e) {
            Notification.show(e.getMessage(), 4000, Notification.Position.MIDDLE);
        }
    }

    private void reload() {
        grid.setItems(travelOrderService.findAll());
        buttonRemoveAll.setEnabled(!travelOrderService.findAll().isEmpty());
    }
}