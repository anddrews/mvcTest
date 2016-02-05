package by.gsu.epamlab.bll.wrapperSql;

public class SelectFromOrdersOnUser extends WrapperSql {

    public SelectFromOrdersOnUser(AbstractQuery query) {
        super(query);
    }

    @Override
    public String getQuery() {
        String result=query.getQuery();
        if(result.contains("WHERE")){result+=" AND ";}
        else {result+=" WHERE ";}
        return result+ " users.user=?";
    }
}
