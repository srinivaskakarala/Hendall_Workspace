
    create table survey.answers (
        Html_Control_Id integer not null,
        User_Survey_Key integer not null,
        Answer varchar(65535),
        Create_Date datetime not null,
        Create_User integer not null,
        Modify_Date datetime,
        Modify_User integer,
        primary key (Html_Control_Id, User_Survey_Key)
    );

    create table survey.survey_lu (
        Survey_Key integer not null auto_increment,
        Create_Date datetime not null,
        Create_User integer not null,
        Modify_Date datetime,
        Modify_User datetime,
        Surver_Version bigint not null,
        Survey_Name varchar(500) not null,
        Survey_Num varchar(45) not null,
        primary key (Survey_Key)
    );

    create table survey.user_survey_access (
        User_Survey_Key integer not null,
        Create_Date datetime,
        Create_User integer,
        Modify_Date datetime,
        Modify_User integer,
        Status varchar(100),
        Survey_key integer not null,
        Survey_Start_Date datetime not null,
        User_Key integer not null,
        primary key (User_Survey_Key)
    );

    alter table survey.answers 
        add constraint FK_8t46ko8pr1uhwnnhq468e9s4t 
        foreign key (User_Survey_Key) 
        references survey.user_survey_access (User_Survey_Key);
