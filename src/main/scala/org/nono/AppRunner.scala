package org.nono

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder

object AppRunner extends App {

  val sparkConf = new SparkConf().setMaster("local[*]").setAppName("TwitterData")
  val streamingContext = new StreamingContext(sparkConf, Seconds(1))

  val cb = new ConfigurationBuilder()
    .setDebugEnabled(false)
    .setOAuthConsumerKey("anRA5wNAqjmoBrFBLEvtQhhz0")
    .setOAuthConsumerSecret("TyaGICIoSg7bAQdDMt73pbDcHmYFBqyVvkLvFSOBDWSPL3SVZq")
    .setOAuthAccessToken("701857675324293121-Bw9fqC9LFZWQRcwpYLXbTztmRD8olAZ")
    .setOAuthAccessTokenSecret("udGJbvZwqdwPWyVVzMELWwZLYUoJxxtTg5ZNglK5mLLa2")

  val auth = new OAuthAuthorization(cb.build)
  val tweets = TwitterUtils.createStream(streamingContext, Some(auth))

  val statuses = tweets
    .map(_.getText())
    .flatMap(_.split(" "))
    .filter(_.contains("a"))
    .map(value => (value.length, value))
    .groupByKey()

  statuses.print()

  streamingContext.start()
  streamingContext.awaitTermination()
}
