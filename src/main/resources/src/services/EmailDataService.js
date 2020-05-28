import http from "../http-common";

class EmailDataService {
  sendEmail(emailAddress, eventName,eventTimeSlot) {
    if(eventName.indexOf(',')!=-1)
    {
        eventName=("" + eventName.substr(0,eventName.indexOf(',')) + eventName.substr(eventName.indexOf(',')+1, eventName.length));
    }
    return http.get(`/sendEmail?params=`+emailAddress+`,`+eventName+`,`+eventTimeSlot);
  }



}

export default new EmailDataService();