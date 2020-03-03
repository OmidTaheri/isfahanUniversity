

package ir.satintech.isfuni.ui.base;



public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    int handleApiError(Throwable throwable);

}
