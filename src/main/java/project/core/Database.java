package project.core;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.entities.CourseEntity;
import project.entities.EnrollmentsEntity;
import project.entities.ExchangeStudentEntity;
import project.entities.GraduateStudentEntity;
import project.entities.HibernateUtil;
import project.entities.StudentEntity;
import project.entities.UndergraduateStudentEntity;

public class Database implements Repo {
    private Session session;
    private Transaction tx;
    /*
    docker run --name mysql-dev \
    -e MYSQL_ROOT_PASSWORD=rootpassword \
    -e MYSQL_DATABASE=myapp \
    -e MYSQL_USER=appuser \
    -e MYSQL_PASSWORD=apppassword \
    -p 3306:3306 \
    -d mysql:8.4
    */

    public Database() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.tx = session.beginTransaction();
    }

    private void Commit() {
        // daroroiye
        // jadid el transcation
        this.tx.commit();
        this.tx = session.beginTransaction();
    }
    
    @Override
    public void SaveData(StudentSystem sys) {
        SaveStudents(sys.getStudents());
        SaveCourses(sys.getCourses());
        SaveEnrollments(sys.getEnrollments());
    }

    private void SaveCourses(ArrayList<Course> courses) {
        for (Course c : courses) {
            CourseEntity ce = new CourseEntity();

            if (c.getCourseId() != 0) {
                ce = session.get(
                    CourseEntity.class,
                    c.getCourseId()
                );

                if (ce == null) {
                    ce = new CourseEntity();
                }

            } else {

                ce = new CourseEntity();
            }


            ce.setCredits(c.getCredits());
            ce.setCourseName(c.getName());
            ce.setInstructorName(c.getInstructorName());

            this.session.merge(ce);
            
        }
        this.Commit();
    }

    private void SaveEnrollments(ArrayList<Enrollment> enrollments) {
        for (Enrollment e : enrollments) {

            StudentEntity student =
                this.session.get(StudentEntity.class, e.getStudentId());

            CourseEntity course =
                this.session.get(CourseEntity.class, e.getCourseId());

            // check if enrollment already exists
            EnrollmentsEntity existing = this.session.createQuery(
                """
                FROM EnrollmentsEntity en
                WHERE en.student.id = :sid
                AND en.course.courseId = :cid
                """,
                EnrollmentsEntity.class
            )
            .setParameter("sid", e.getStudentId())
            .setParameter("cid", e.getCourseId())
            .uniqueResult();

            if (existing != null) {
                continue;
            }

            EnrollmentsEntity el = new EnrollmentsEntity();

            el.setAttendance(e.getAttendance());
            el.setCourse(course);
            el.setStudent(student);
            el.setGrade(e.getGrade());

            this.session.persist(el);
        }

        this.Commit();
    }

    private void SaveStudents(ArrayList<Student> students) {
        for (Student s : students) {
            if(s.getClass().getSimpleName().equals("UndergraduateStudent")) {   //“What class is this object REALLY from?”
                UndergraduateStudent u = (UndergraduateStudent) s;
                UndergraduateStudentEntity use = new UndergraduateStudentEntity();

                if (u.getStudentId() != 0) {
                    use = session.get(
                        UndergraduateStudentEntity.class,
                        u.getStudentId()
                    );

                    if (use == null) {
                        use = new UndergraduateStudentEntity();
                    }

                } else {

                    use = new UndergraduateStudentEntity();
                }

                use.setFullName(u.getFullName());
                use.setEmail(u.getEmail());
                use.setMajor(u.getMajor());

                use.setYearLevel(u.getYearLevel());

                this.session.merge(use);
            }
    
            else if(s.getClass().getSimpleName().equals("GraduateStudent")) {
                GraduateStudent g = (GraduateStudent) s;
                GraduateStudentEntity gs = new GraduateStudentEntity();

                if (g.getStudentId() != 0) {
                    gs = session.get(
                        GraduateStudentEntity.class,
                        g.getStudentId()
                    );

                    if (gs == null) {
                        gs = new GraduateStudentEntity();
                    }

                } else {

                    gs = new GraduateStudentEntity();
                }

                gs.setFullName(g.getFullName());
                gs.setEmail(g.getEmail());
                gs.setMajor(g.getMajor());

                gs.setSupervisorName(g.getSupervisorName());
                gs.setThesisTitle(g.getThesisTitle());

                this.session.merge(gs);
            }
    
            else if(s.getClass().getSimpleName().equals("ExchangeStudent")) {
                ExchangeStudent x = (ExchangeStudent) s;
                ExchangeStudentEntity xe = new ExchangeStudentEntity();

                if (x.getStudentId() != 0) {
                    xe = session.get(
                        ExchangeStudentEntity.class,
                        x.getStudentId()
                    );

                    if (xe == null) {
                        xe = new ExchangeStudentEntity();
                    }

                } else {

                    xe = new ExchangeStudentEntity();
                }

                xe.setFullName(x.getFullName());
                xe.setEmail(x.getEmail());
                xe.setMajor(x.getMajor());

                xe.setExchangePeriod(x.getExchanegPeriod());
                xe.setHomeUniversity(x.getHomeUniversity());
    
                this.session.merge(xe);
            }
        }
        this.Commit();
    }

    @Override
    public void LoadData(StudentSystem sys) {
        LoadStudents(sys);
        LoadCourses(sys);
        LoadEnrollments(sys);
    }

    public void LoadStudents(StudentSystem sys) {

        List<StudentEntity> students = session.createQuery("FROM StudentEntity", StudentEntity.class).list();

        for (StudentEntity se : students) {

            if (se instanceof UndergraduateStudentEntity u) {

                UndergraduateStudent s = new UndergraduateStudent();

                s.setStudentId(u.getId());
                s.setFullName(u.getFullName());
                s.setEmail(u.getEmail());
                s.setMajor(u.getMajor());

                s.setYearLevel(u.getYearLevel());

                sys.addStudent(s);
            }

            else if (se instanceof GraduateStudentEntity g) {

                GraduateStudent s = new GraduateStudent();

                s.setStudentId(g.getId());
                s.setFullName(g.getFullName());
                s.setEmail(g.getEmail());
                s.setMajor(g.getMajor());

                s.setSupervisorName(g.getSupervisorName());
                s.setThesisTitle(g.getThesisTitle());

                sys.addStudent(s);
            }

            else if (se instanceof ExchangeStudentEntity x) {

                ExchangeStudent s = new ExchangeStudent();

                s.setStudentId(x.getId());
                s.setFullName(x.getFullName());
                s.setEmail(x.getEmail());
                s.setMajor(x.getMajor());

                s.setHomeUniversity(x.getHomeUniversity());
                s.setExchangePeriod(x.getExchangePeriod());

                sys.addStudent(s);
            }
        }
    }

    private void LoadCourses(StudentSystem sys) {

        List<CourseEntity> courses =session.createQuery("FROM CourseEntity",CourseEntity.class).list();

        for (CourseEntity ce : courses) {

            Course c = new Course();

            c.setCourseId(ce.getCourseId());
            c.setCouresName(ce.getCourseName());
            c.setCredits(ce.getCredits());
            c.setInstructorName(ce.getInstructorName());

            sys.addCourse(c);
        }
    }
    
    private void LoadEnrollments(StudentSystem sys) {

        List<EnrollmentsEntity> enrollments = session.createQuery("FROM EnrollmentsEntity", EnrollmentsEntity.class).list();

        for (EnrollmentsEntity ee : enrollments) {

            if (ee.getStudent() == null) {
                continue;
            }

            if (ee.getCourse() == null) {
                continue;
            }

            int studentId = ee.getStudent().getId();
            int courseId = ee.getCourse().getCourseId();
            double grade = ee.getGrade();
            double attendance = ee.getAttendance();

            sys.EnrollStudent(studentId, courseId);
            sys.UpdateAttendance(studentId, courseId, attendance);
            sys.UpdateGrade(studentId, courseId, grade);
        }
    }

    @Override
    public void SaveReport(StudentSystem sys) {
        /// ... nothing
    }

    @Override
    public void Close() {
        session.close();
        System.err.println("connection to db closed");
    }

    public List<CourseEntity> findCoursesByStudent(int studentId) {
        List<CourseEntity> result = this.session.createQuery(
            "SELECT e.course FROM EnrollmentsEntity e WHERE e.student.studentId = :sid", CourseEntity.class)
            .setParameter("sid", studentId)
            .getResultList();
        return result;
    }

    public List<StudentEntity> findStudentsByCourse(int courseId) {
        List<StudentEntity> result = this.session.createQuery(
            "SELECT e.student FROM EnrollmentsEntity e WHERE e.course.courseId = :cid", StudentEntity.class)
            .setParameter("cid", courseId)
            .getResultList();
        return result;
    }

    public double getAverageGrade(int courseId) {
        Double result = this.session.createQuery(
            "SELECT AVG(e.grade) FROM EnrollmentsEntity e WHERE e.course.courseId = :cid", Double.class)
            .setParameter("cid", courseId)
            .getSingleResult();

        if (result == null) {
            return 0.0;
        }
        return result;
    }

    public StudentEntity getTopStudent() {
        List<StudentEntity> result = this.session.createQuery(
            "SELECT e.student FROM EnrollmentsEntity e GROUP BY e.student ORDER BY AVG(e.grade) DESC", StudentEntity.class)
            .setMaxResults(1)
            .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

}
