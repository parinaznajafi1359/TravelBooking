package com.example.application.views.travelbooking;

import com.example.application.views.MainLayout;
import com.example.application.views.home.HomeView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.OptionalDouble;

@PageTitle("TravelBooking")
@Route(value = "travelbooking", layout = MainLayout.class)
@Menu(order = 1, icon = LineAwesomeIconUrl.FILE)
public class TravelBookingView extends VerticalLayout {

    public TravelBookingView() {
        setSpacing(false);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(HomeView.getHeader());

        H2 title = new H2("Reiseangebote");
        add(title);

        VerticalLayout zone1 = createCard("Wien - Italien", 199.0, OptionalDouble.of(0));
        VerticalLayout zone2 = createCard("Wien - Paris", 299.0, OptionalDouble.of(0));
        VerticalLayout zone3 = createCard("Wien - Barcelona", 349.0, OptionalDouble.of(0));
        VerticalLayout zone4 = createCard("Wien - Dubai", 599.0, OptionalDouble.empty());

        FlexLayout cardsLayout = new FlexLayout(zone1, zone2, zone3, zone4);
        cardsLayout.setWidthFull();
        cardsLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        cardsLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        add(cardsLayout);

        Paragraph info = new Paragraph(
                "Unsere Reiseangebote beinhalten je nach Paket Hotel, Flug, Frühstück oder Transfer. " +
                        "Die Angebote können später erweitert und mit einer Datenbank verbunden werden."
        );
        info.setWidth("80%");
        info.getStyle()
                .set("text-align", "center")
                .set("font-size", "20px");

        add(info);
    }

    private VerticalLayout createCard(String destination, double price, OptionalDouble specialOffer) {
        H2 destinationTitle = new H2(destination);
        Paragraph priceText = new Paragraph("Preis: " + price + " Euro");

        String text = specialOffer.isPresent()
                ? "Sonderangebot verfügbar"
                : "Kein Sonderangebot";

        Paragraph offerText = new Paragraph(text);

        VerticalLayout card = new VerticalLayout(destinationTitle, priceText, offerText);
        card.setWidth("350px");
        card.setPadding(true);
        card.setSpacing(false);
        card.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        card.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px")
                .set("box-shadow", "2px 2px 8px lightgray");

        return card;
    }
}