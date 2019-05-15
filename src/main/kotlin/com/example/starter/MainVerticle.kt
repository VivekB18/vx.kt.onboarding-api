package com.example.starter


import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch

class MainVerticle : CoroutineVerticle() {

  //Call your api methods here
  override suspend fun start() {

  }

  //This is needed to make coroutines work. Don't worry about how it works for now.
  fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
    handler { ctx ->
      launch(ctx.vertx().dispatcher()) {
        try {
          fn(ctx)
        } catch (e: Throwable) {
          ctx.fail(e)
        }
      }
    }
  }
}
