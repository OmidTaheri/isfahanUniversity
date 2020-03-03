

package ir.satintech.isfuni.data;


import ir.satintech.isfuni.data.db.DbHelper;
import ir.satintech.isfuni.data.network.ApiHelper;
import ir.satintech.isfuni.data.prefs.PreferencesHelper;

import io.reactivex.Observable;



public interface DataManager extends DbHelper, PreferencesHelper,ApiHelper {




}
