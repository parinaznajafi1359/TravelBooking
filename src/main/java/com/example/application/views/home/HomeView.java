package com.example.application.views.home;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;

@PageTitle("Home")
@Route("Home")
@Menu(order = 0, icon = LineAwesomeIconUrl.GLOBE_SOLID)
public class HomeView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HomeView() {
        setSpacing(false);
        setAlignItems(Alignment.CENTER);

        H1 companyName = new H1("TravelBooking");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... your travel booking place ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        Image img = new Image("images/logo.png", "TravelBooking Logo");
        img.setWidth("220px");

        Paragraph description = new Paragraph(
                "Bei TravelBooking können Kunden einfache und moderne Reisen buchen. " +
                        "Die Anwendung zeigt verschiedene Angebote, Preise und Informationen " +
                        "übersichtlich an. Ziel ist es, eine einfache Webanwendung mit Vaadin " +
                        "und Spring Boot zu erstellen."
        );
        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        H3 name = new H3("TravelBooking GmbH");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");

        add(companyName, subName, img, description, name, street, city);
    }

}
