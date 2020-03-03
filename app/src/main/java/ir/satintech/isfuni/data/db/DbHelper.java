/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package ir.satintech.isfuni.data.db;

import java.util.List;

import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.data.db.model.Location;




public interface DbHelper {


    public List<Location> ListAllLocations();


    public Location SelectLocation(Long id);

    public List<Category> ListAllCategories();

    public List<Location> getLocationByCategory(Long category_id);

    public void insertLocation(Location item);

    public void insertCategory(Category item);

    public List<Location> searchName(String query);

}
