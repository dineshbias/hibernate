package com;

import com.constant.Qualification;
import com.entity.Student;
import com.entity.Subject;
import com.entity.Teacher;
import com.repository.StudentRepository;
import com.repository.SubjectRepositry;
import com.repository.TeacherRepository;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SbJpaHibernateApplication implements CommandLineRunner {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private TeacherRepository teacherRepositry;

  @Autowired
  private SubjectRepositry subjectRepositry;

  public static void main(String[] args) {
    System.out.println("                                          **************************************************");
    SpringApplication.run(SbJpaHibernateApplication.class, args);
    // printAllBeans(ctx);
    System.out.println("                                          **************************************************");

    System.out.println("                                          **************************************************");
  }

  public static void printAllBeans(ApplicationContext ctx) {
    System.out.println(ctx.getApplicationName() + " " + ctx.getId());
    for (String beanName : ctx.getBeanDefinitionNames()) {
      System.out.println(beanName + " " + ctx.getBean(beanName));
    }
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(Arrays.toString(args) + "-------");

    insertSubjects();
    insertTeachers();
    insertStudents();

    System.out.println("                                          **************************************************");
    studentRepository.findAll().forEach((student) -> System.out.println(student));
    System.out.println("                                          **************************************************");
    subjectRepositry.findAll().forEach((subjects) -> System.out.println(subjects));
    System.out.println("                                          **************************************************");
    teacherRepositry.findAll().forEach((teachers) -> System.out.println(teachers));
  }

  public void insertSubjects() {
    System.out.println("                                          **************************************************");

    Subject subject1 = new Subject();
    subject1.setSubName("Maths");
    subjectRepositry.save(subject1);

    Subject subject2 = new Subject();
    subject2.setSubName("Social Science");
    subjectRepositry.save(subject2);

    Subject subject3 = new Subject();
    subject3.setSubName("English");
    subjectRepositry.save(subject3);

    System.out.println("******** save subject                           ******************************************");

  }

  public void insertTeachers() {
    System.out.println("                                          **************************************************");
    Teacher teacher1 = new Teacher();
    teacher1.setName("Dinesh");
    LocalDate dateOfBirth1 = LocalDate.of(1978, 7, 22);
    teacher1.setDateOfBirth(dateOfBirth1);
    teacherRepositry.save(teacher1);

    Teacher teacher2 = new Teacher();
    teacher2.setName("Sunita");
    LocalDate dateOfBirth2 = LocalDate.of(1968, 7, 10);
    teacher2.setDateOfBirth(dateOfBirth2);
    teacher2.setQualification(Qualification.NTT);
    teacherRepositry.save(teacher2);

    Teacher teacher3 = new Teacher();
    teacher3.setName("Dinesh");
    LocalDate dateOfBirth3 = LocalDate.of(1978, 7, 22);
    teacher3.setDateOfBirth(dateOfBirth3);
    teacher3.setQualification(Qualification.MED);
    teacherRepositry.save(teacher3);

    System.out.println("************* save teacher                           *************************************");
  }

  public void insertStudents() {
    System.out.println("                                          **************************************************");
    Student s = new Student();
    s.setAge(12);
    s.setName("Mahesh");
    studentRepository.save(s);
    System.out.println("******** save Student                           ******************************************");
  }
}
