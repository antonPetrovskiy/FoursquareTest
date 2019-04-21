package work.test.com.testwork.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResponse {
    @SerializedName("response")
    @Expose
    private Group group;

    public Group getGroup() {
        return group;
    }

    public class Group {
        @SerializedName("groups")
        private List<Item> groups;

        public List<Item> getGroups() {
            return groups;
        }

        public class Item {
            @SerializedName("items")
            private List<Venues> items;

            public List<Venues> getItems() {
                return items;
            }
        }
    }


}
