package ectimel.cqrs.queries;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class QueryDispatcherImpl implements QueryDispatcher, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    @Override
    public <TResult> CompletableFuture<TResult> query(Query<TResult> query) {
        String queryName = query.getClass().getSimpleName();
        queryName = queryName.substring(0, 1).toLowerCase() + queryName.substring(1);

        Object handlerBean = applicationContext.getBean(queryName + "Handler");
        if (handlerBean instanceof QueryHandler) {
//            try {
//                Type queryGenericInterface = query.getClass().getGenericInterfaces()[0];
//                if(handlerBean.getClass().getGenericInterfaces()[0].equals(queryGenericInterface)) {
                
                QueryHandler<Query<TResult>, TResult> handler = (QueryHandler<Query<TResult>, TResult>) handlerBean;
//                }
                return handler.handle(query);
//            } catch (Exception e) {
//                
//            }

        }
        throw new IllegalArgumentException("Wrong handler");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
