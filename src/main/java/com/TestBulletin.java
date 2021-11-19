package com;

import com.domain.*;
import com.service.*;
import com.service.impl.AdvertisementServiceImpl;
import com.service.impl.AuthorServiceImpl;
import com.service.impl.CategoryServiceImpl;
import com.service.impl.MatchingServiceImpl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestBulletin {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bulletin_board");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Email gmail = Email.builder()
                .email("gmail.com")
                .build();
        Email yahoo = Email.builder()
                .email("yahoo.com")
                .build();
        Email mail = Email.builder()
                .email("mail.ru")
                .build();
        Email rambler = Email.builder()
                .email("rambler.ru")
                .build();

        Phone number1 = Phone.builder()
                .phone("088")
                .build();
        Phone number2 = Phone.builder()
                .phone("095")
                .build();
        Phone number3 = Phone.builder()
                .phone("077")
                .build();
        Phone number4 = Phone.builder()
                .phone("777")
                .build();
        Phone number5 = Phone.builder()
                .phone("999")
                .build();

        Address orsk = Address.builder()
                .city("orsk")
                .build();
        Address peterburg = Address.builder()
                .city("Peterburg")
                .build();
        Address moscow = Address.builder()
                .city("moscow")
                .build();
        Address berlin = Address.builder()
                .city("berlin")
                .build();
        Address london = Address.builder()
                .city("london")
                .build();

        Author dasha = Author.builder()
                .name("Masha")
                .email(gmail)
                .phones(List.of(number4, number5))
                .address(peterburg)
                .build();
        number4.setAuthor(dasha);
        number5.setAuthor(dasha);

        Author vadim = Author.builder()
                .name("Vadimussssssssssssssss")
                .email(rambler)
                .phones(List.of(number1, number2))
                .address(berlin)
                .build();
        number1.setAuthor(vadim);
        number2.setAuthor(vadim);


/*
        artem.setAddress(peterburg);
        artem.addPhones(number1);
        artem.addPhones(number4);

        makar.setAddress(orsk);
        makar.addPhones(number7);

        bill.setAddress(berlin);
        bill.addPhones(number6);

        kirill.setAddress(london);
        kirill.addPhones(number7);*/

//        number1.setAuthor(artem);

   /*     number4.setAuthor(artem);
        number7.setAuthor(makar);
        number6.setAuthor(bill);
        number7.setAuthor(kirill);


        artem.setEmail(yahoo);
        makar.setEmail(mail);

        bill.setEmail(rambler);
        kirill.setEmail(yahoo);
*/

//        em.persist(bill);
//        em.persist(kirill);


        // найти автора по id
        Author authorLot1 = em.getReference(Author.class, 57);
        // найти категорию по id
        Category categoryLot1 = em.getReference(Category.class, 35);

//        Advertisement lot1 = new Advertisement("Nissan", 2019 - 01 - 01, "продам",
//                5.500, author);
        //  lot1.setCategory(category);
        //  em.persist(lot1);



   /*     Advertisement lot1 = new Advertisement("House", LocalDate.of(2021, 6, 11), "продам дом",
                BigDecimal.valueOf(0.9));
        Advertisement lot2 = new Advertisement("Игрушки", LocalDate.of(2019, 5, 01), "продам игрушки",
                BigDecimal.valueOf(0.5));
        Advertisement lot3 = new Advertisement("Avto", LocalDate.of(2017, 6, 9), "продам авто",
                BigDecimal.valueOf(5.5));
        Advertisement lot4 = new Advertisement("Moto", LocalDate.of(2016, 5, 8), "продам мотоцикл",
                BigDecimal.valueOf(2));
        Advertisement lot5 = new Advertisement("Velo", LocalDate.of(2020, 4, 7), "продам вело",
                BigDecimal.valueOf(3.5));*/

        Advertisement lot5 = Advertisement.builder()
                .name("Phone")
                .dateOfPublic(LocalDate.of(2021, 4, 7))
                .text("продам")
                .price(BigDecimal.valueOf(1.5))
                .build();
        lot5.setCategory(categoryLot1);
        lot5.setAuthor(authorLot1);


//        Category category = em.getReference(Category.class, 9);
//        lot1.setCategory(category);
//        Author Newauthor = em.getReference(Author.class, 64);
//        lot1.setAuthor(Newauthor);
        // category.setName("Velo");


        // в периоде транзакции меняем значение
//        category.setName("Motor");

//       Author authorNew = em.getReference(Author.class, 11);
//       authorNew.setName("Mark");
//
        // изменить категориию в периоде транзакции
        //    Advertisement advertisement = em.getReference(Advertisement.class, 80);
        // advertisement.setAuthor(authorNew);
        //   Category category = em.getReference(Category.class, 6);
        //  advertisement.setCategory(category);

//        category.setName("Velo");

        //       em.persist(lot1);
        //       em.persist(advertisement);


        Author authorNew = em.getReference(Author.class, 131);
        Category categoryNew = em.getReference(Category.class, 216);


        CRUDService<Category> service = new CategoryServiceImpl();


        //    Category newCaregory = new Category("Furniture");

        Category newCaregory = Category.builder()
                .name("Comp")
                .build();
//        service.save(newCaregory);

        Category category = service.findById(45);
//        System.out.println(category);

        //     category.setName("Toys");

        //    service.update(category);

   //     service.deleteById(266);


        AuthorService author = new AuthorServiceImpl();

        //   author.save(dasha);
        //   author.save(vadim);

   /*    Author author1 = author.findById(15);
        System.out.println(author1);*/

        //    authorNew.setName("Dasha");
        //  author.update(authorNew);
        //       author.deleteById(92);


   /*     List<Advertisement> author1= ((AuthorServiceImpl) author).findAdvertisementByIdAuthor(List.of(15, 36));
        for (Object x : author1){
            System.out.println(x);
        }*/


        AdvertisementService advertisement = new AdvertisementServiceImpl();
        // advertisement.save(lot5);

        Advertisement advertisementNew = advertisement.findById(64);
        //   System.out.println(advertisementNew);
   /*     advertisementNew.setAuthor(authorNew);
        advertisementNew.setCategory(categoryNew);
        advertisement.update(advertisementNew);*/


//        advertisementNew.setName("Moto");
//        advertisement.update(advertisementNew);
//        advertisement.update(advertisementNew2);


/*        List<Advertisement> searchByCategories = advertisement.findAdvertisementByCategories(List.of(44, 14));
        for (Object x : searchByCategories) {
            System.out.println(x);
        }*/

/*        List searchByWord = advertisement.searchByWord("авто");
        for (Object x : searchByWord){
            System.out.println(x);
        }*/

 /*       List searchByDate = advertisement.searchByDate(LocalDate.of(2016, 5, 8));
        for (Object x : searchByDate){
            System.out.println(x);
        }*/


        //  advertisement.deleteAdvertisementByAuthor(131);

        //  advertisement.deleteById(192);


        MatchingAdService matchingAdService = new MatchingServiceImpl();


        MatchingAd matchingAd = MatchingAd
                .builder()
                .title("продам велосипед")
                .priceFrom(BigDecimal.valueOf(1))
                .priceTo(BigDecimal.valueOf(2))
                .category(categoryNew)
                .author(authorNew)
                .build();
        //  MatchingAd matchingAd = new MatchingAd("продам вело", BigDecimal.valueOf(3), BigDecimal.valueOf(4), categoryNew, authorNew);

//       matchingAdService.save(matchingAd);


//        em.persist(Lot2);
//        em.persist(Lot3);

        // сохранить новое объявление с сущ автором
        // em.persist(Lot4);

        // сохранить новое объявление с сущ автором и категорией
        // em.persist(Lot5);

        tran.commit();

        em.close();

        factory.close();
    }
}
