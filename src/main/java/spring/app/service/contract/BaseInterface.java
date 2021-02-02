package spring.app.service.contract;

public interface BaseInterface {

    <T> T findEntityById(Long id);

    void seedEntities();

    int getCount();
}
