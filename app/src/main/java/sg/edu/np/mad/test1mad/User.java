package sg.edu.np.mad.test1mad;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(){

    }

    public User(String Name, String Desc, int Id, boolean Followed){
        name = Name;
        description = Desc;
        id = Id;
        followed = Followed;
    }

    public void setUserName(String name){
        this.name = name;
    }
    public String getUserName(){
        return this.name;
    }

    public void setUserDescription(String description){
        this.description = description;
    }
    public String getUserDescription(){
        return this.description;
    }

    public void setUserId(int id){
        this.id = id;
    }
    public int getUserId(){
        return this.id;
    }

    public void setUserFollowed(boolean followed){
        this.followed = followed;
    }
    public boolean getUserFollowed(){
        return this.followed;
    }

}