package com.example.application.views.travelbooking;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
@PageTitle("TravelBooking")
@Route("")
@Menu(order = 1, icon = LineAwesomeIconUrl.FILE)
public class TravelBookingView extends VerticalLayout {

    public TravelBookingView() {
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

        H2 title = new H2("Reiseangebote");

        H2 zone1 = new H2("Wien - Italien");
        Paragraph price1 = new Paragraph("Preis: 199,00 Euro");
        Paragraph free1 = new Paragraph("Inklusive Hotel und Frühstück");

        H2 zone2 = new H2("Wien - Paris");
        Paragraph price2 = new Paragraph("Preis: 299,00 Euro");
        Paragraph free2 = new Paragraph("Inklusive Flug und Hotel");

        H2 zone3 = new H2("Wien - Barcelona");
        Paragraph price3 = new Paragraph("Preis: 349,00 Euro");
        Paragraph free3 = new Paragraph("Inklusive Flug, Hotel und Stadtführung");

        H2 zone4 = new H2("Wien - Dubai");
        Paragraph price4 = new Paragraph("Preis: 599,00 Euro");
        Paragraph free4 = new Paragraph("Luxusreise mit Hotel und Transfer");

        add(
                companyName, subName, title,
                zone1, price1, free1,
                zone2, price2, free2,
                zone3, price3, free3,
                zone4, price4, free4
        );
    }

}
