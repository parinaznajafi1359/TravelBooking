package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getHeader());


        Paragraph description = new Paragraph(
                "Bei TravelBooking können Kunden einfache und moderne Reisen buchen. " +
                        "Die Anwendung zeigt verschiedene Angebote, Preise und Informationen übersichtlich an. " +
                        "Mit Vaadin und Spring Boot wird die Webanwendung komplett in Java erstellt."
        );
        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        HorizontalLayout content = new HorizontalLayout(description);
        content.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        content.getStyle().set("gap", "40px");
        add(content);

        H3 name = new H3("TravelBooking GmbH");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");

        HorizontalLayout address = new HorizontalLayout(name, street, city);
        address.getStyle().set("gap", "40px");
        add(address);
    }

    public static Component getHeader() {
        H1 companyName = new H1("TravelBooking");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... your travel booking place ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        VerticalLayout headerLayout = new VerticalLayout(companyName, subName);
        headerLayout.setSpacing(false);
        headerLayout.setPadding(false);
        headerLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return headerLayout;
    }
}