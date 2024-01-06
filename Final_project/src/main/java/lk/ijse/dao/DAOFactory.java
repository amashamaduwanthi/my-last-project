package lk.ijse.dao;

import lk.ijse.dao.Custom.Impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType{
        ADMIN,CLASS,CLASSSUBJECTDETAILS,EXAM,HALL,LECTURER,LECTURERSUBJECT,PARENT,PAYMENT,REGISTER,SCHEDULE,STUDENTCLASS,STUDENT,SUBJECT;
    }
    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
                case ADMIN:
                return new AdminDAOImpl();
                case CLASS:
                return new ClassDAOImpl();
                case CLASSSUBJECTDETAILS:
                return new ClassSubjectDetailsDAOImpl();
                case EXAM:
                return new ExamDAOImpl();
                case HALL:
                return new HallDAOImpl();
                case LECTURER:
                return new LecturerDAOImpl();
                case LECTURERSUBJECT:
                return new LecturerSubjectDAOImpl();
                case PARENT:
                return new ParentDAOImpl();
                case PAYMENT:
                return new PaymentDAOImpl();
                case REGISTER:
                return new RegisterDAOImpl();
                case SCHEDULE:
                return new ScheduleDAOImpl();
                case STUDENT:
                return new StudentDAOImpl();
                case SUBJECT:
                return new SubjectDAOImpl();
                case STUDENTCLASS:
                return new StudentClassDAOImpl();
                default:
                return null;
        }

    }
}


