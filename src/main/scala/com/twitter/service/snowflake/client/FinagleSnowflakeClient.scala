package com.twitter.service.snowflake.client

import com.twitter.finagle._
import com.twitter.finagle.builder._
import com.twitter.finagle.thrift._
import com.twitter.service.snowflake._
import com.twitter.service.snowflake.gen._
import org.apache.thrift.protocol._

/**
 * @author Daegeun Kim (dani.kim@geekple.com)
 */
object FinagleSnowflakeClient {
  def apply(hosts: String, connectionLimit: Int = 1) = {
    val service: Service[ThriftClientRequest, Array[Byte]] = ClientBuilder().hosts(hosts).codec(ThriftClientFramedCodec()).hostConnectionLimit(connectionLimit).build()
    new Snowflake.ServiceToClient(service, new TBinaryProtocol.Factory)
  }
}
