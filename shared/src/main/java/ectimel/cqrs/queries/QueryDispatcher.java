package ectimel.cqrs.queries;

import java.util.concurrent.CompletableFuture;

public interface QueryDispatcher {
    <TResult> CompletableFuture<TResult> query(Query<TResult> query);
}
