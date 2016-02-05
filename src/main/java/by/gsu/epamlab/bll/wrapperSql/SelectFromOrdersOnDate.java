package by.gsu.epamlab.bll.wrapperSql;

public class SelectFromOrdersOnDate extends WrapperSql {

    public SelectFromOrdersOnDate(AbstractQuery query) {
        super(query);
    }

    @Override
    public String getQuery() {
        String result=query.getQuery();
        if(result.contains("WHERE")){result+=" AND ";}
        else {result+=" WHERE ";}
        return result+ " TO_DAYS( repertoire.date_play ) = TO_DAYS(  ? ) ";
    }
}
