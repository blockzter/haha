#!/usr/bin/env bash
declare dbuser="admin";
declare dbpass="Square!001";
declare dbhost="zwpi:5984";
declare dbname="zwave_events";

declare DESIGN="_design";




curl -X PUT 'http://admin:Square!001@zwpi:5984/zwave_events/_design/by_ids' -d "$(cat byY.js)"

