package com;

import com.config.ConfigApp;
import com.dao.impl.MatchingDAOImpl;
import com.domain.*;
import com.service.*;
import com.service.impl.AdvertisementServiceImpl;
import com.service.impl.AuthorServiceImpl;
import com.service.impl.CategoryServiceImpl;
import com.service.impl.MatchingServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Transactional
public class TestBulletin {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);


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
                .email(mail)
                .phones(List.of(number4, number5))
                .address(orsk)
                .build();
        number4.setAuthor(dasha);
        number5.setAuthor(dasha);

        Author vadim = Author.builder()
                .name("Masha")
                .email(rambler)
                .phones(List.of(number5))
                .address(moscow)
                .build();
        number5.setAuthor(vadim);
      //  number2.setAuthor(vadim);



        // найти автора по id
//        Author authorLot1 = em.getReference(Author.class, 57);
        // найти категорию по id
//        Category categoryLot1 = em.getReference(Category.class, 35);

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


        EntityManagerFactory bean = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = bean.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Category category1 = entityManager.find(Category.class, 1);
        System.out.println(category1);

        transaction.commit();
//        entityManager.close();




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


       /* Author authorNew = em.getReference(Author.class, 1);
        System.out.println(authorNew);*/
    //   Category categoryNew = em.getReference(Category.class, 216);


        CRUDService<Category> categoryService = context.getBean(CategoryServiceImpl.class);


        Category categorynew = Category.builder()
                .name("House")
                .build();
 //       categoryService.save(categorynew);

        Category category = categoryService.findById(7);
        System.out.println(category);

        //     category.setName("Toys");

        //    service.update(category);

        //     service.deleteById(266);


        AuthorService authorService = context.getBean(AuthorServiceImpl.class);
      //  authorService.save(vadim);
        Author author = authorService.findById(3);

      //  System.out.println(author);

        // authorNew.setName("Dasha");
        // author.update(authorNew);
        // author.deleteById(267);


   /*     List<Advertisement> author1= ((AuthorServiceImpl) author).findAdvertisementByIdAuthor(List.of(15, 36));
        for (Object x : author1){
            System.out.println(x);
        }*/


        AdvertisementService advertisementService = context.getBean(AdvertisementServiceImpl.class);


        Advertisement lot1 = Advertisement.builder()
                .name("House")
                .dateOfPublic(LocalDate.of(2020, 3, 10))
                .text("продам дом")
                .price(BigDecimal.valueOf(1.5))
                .build();
        lot1.setCategory(category);
        lot1.setAuthor(author);

 //       advertisementService.save(lot1);



       //        Advertisement advertisementNew = advertisement.findById(64);
        //System.out.println(advertisementNew);
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

        //  advertisement.deleteById(272);

//        CRUDService<MatchingAd> matchingService = context.getBean(MatchingServiceImpl.class);


     /*   MatchingAd matchingAdNew = MatchingAd
                .builder()
                .title("продам дом")
                .priceFrom(BigDecimal.valueOf(1))
                .priceTo(BigDecimal.valueOf(2))
                .category(category)
                .author(author)
                .build();*/
        //  MatchingAd matchingAd = new MatchingAd("продам вело", BigDecimal.valueOf(3), BigDecimal.valueOf(4), categoryNew, authorNew);

     //  matchingService.save(matchingAdNew);

     //   matchingService.findById(5);


//        em.persist(Lot2);
//        em.persist(Lot3);

        // сохранить новое объявление с сущ автором
        // em.persist(Lot4);

        // сохранить новое объявление с сущ автором и категорией
        // em.persist(Lot5);
//        bean.close();

    }
}
