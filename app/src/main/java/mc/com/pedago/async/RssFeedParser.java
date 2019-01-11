package mc.com.pedago.async;

import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public  class RssFeedParser {

    private static final String TAG = "async";
    private String[] tags={"title","description","link","pubDate","enclosure"};
    private HashMap<String,String> data= new HashMap<>();

    public RssResponse Parse(InputStream inputStream) throws XmlPullParserException, IOException {

        RssResponse response;
        List<RssModel> items = new ArrayList<RssModel>();
        RssModel item;

        String header_title, header_desc, header_link;
        String content="" ;
        String image="";
        Date pubDate;
        boolean isheader=true;

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            header_title=header_desc=header_link=null;

            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();
                String name = xmlPullParser.getName();

                if (name == null)
                    continue;

                if (eventType == XmlPullParser.START_TAG)
                    if (name.equalsIgnoreCase("item")) {
                        isheader=false;
                        continue;
                    }

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        pubDate = getDate(data.get("pubDate"));
                        image = data.containsKey("image") ? data.get("image") : "";
                        item = new RssModel(data.get("title"), data.get("link"), data.get("description"), pubDate, image);
                        items.add(item);
                    } /*else {
                        if (!isheader) {
                            Log.i(TAG, "*********************************Parse - HEADER*********************************");
                            Log.i(TAG, "Parse: Header ==> "+header_title+" | "+header_link+" | "+header_desc);
                            Log.i(TAG, "*********************************Parse - HEADER*********************************");
                        }
                    }*/

                   /* Log.i(TAG, "*********************************Parse - END_TAG*********************************");
                       for (Map.Entry<String,String> entry : data.entrySet() )
                            Log.i(TAG, "Parse: Item (Map) ==> "+entry.getKey()+" = "+entry.getValue());
                    Log.i(TAG, "*********************************Parse - END_TAG*********************************");*/

                    continue;
                }

                content = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    content = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                } else {
                    if (name.equalsIgnoreCase("enclosure")) {
                        content = xmlPullParser.getAttributeValue(null, "url");
                        data.put("image", content);
                    }
                }

                if(isheader){
                    if (name.equalsIgnoreCase("title"))
                        header_title=content;
                    if (name.equalsIgnoreCase("link"))
                        header_link=content;
                    if (name.equalsIgnoreCase("description"))
                        header_desc=content;
                }else {
                    if (Arrays.asList(tags).contains(name))
                        if (!name.equalsIgnoreCase("enclosure"))
                            data.put(name, content);
                }

            }
            response = new RssResponse(items,header_title, header_desc, header_link);
            return response;
        } finally {
            inputStream.close();
        }
    }

    //todo : copy to tools
    private Date getDate(String date) {
        DateFormat format = new SimpleDateFormat("MMMM d M yyyy h:m:s", Locale.ENGLISH);
        try {
            return format.parse(date);
        } catch (ParseException e) {
           return new Date();
        }
    }
}