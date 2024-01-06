package lk.ijse.bao.custom;

import lk.ijse.bao.custom.impl.*;
import lk.ijse.dao.Custom.Impl.ExamDAOImpl;
import lk.ijse.dao.SuperDAO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public void getBO() {

    }

    public enum BOType {
       CLASS,DASHBOARD,EXAM,LECTURER,PAYMENT,SCHEDULE,STAFF,STUDENT,STUDENTCLASSREGISTRATION,SUBJECT;

    }

    public SuperDAO getBO(BOType boType) {
        switch (boType) {
            case CLASS:
                return new ClassBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
                case EXAM:
                    return new ExamBOImpl();
                    case LECTURER:
                        return new LecturerBOImpl();
                        case
                        PAYMENT:
                            return new PaymentBOImpl();
                            case
                            SCHEDULE:
                                return new ScheduleBOImpl();
                                case
                                STAFF:
                                    return new StaffBOImpl();
                                    case
                                    STUDENT:
                                        return new StudentBOImpl();
                                        case
                                        STUDENTCLASSREGISTRATION:
                                            return new StudentClassRegistrationBOImpl();
                                            case
                                            SUBJECT:
                                                return new SubjectBOImpl();

                                            default:
                                                return null;
        }
    }
}

