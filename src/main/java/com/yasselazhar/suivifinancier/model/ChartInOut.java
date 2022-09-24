package com.yasselazhar.suivifinancier.model;

//Pour les requête JPQL query nous utilisons les projections (en passant par des interfaces)
public interface ChartInOut {
    String getYearMonth();
    int getSommeMontant();
}