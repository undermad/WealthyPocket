package wallet.cqrs.queries;

import java.util.concurrent.CompletableFuture;

public interface QueryHandler<TQuery extends Query<TResult>, TResult> {
    CompletableFuture<TResult> handle(TQuery query);
}
