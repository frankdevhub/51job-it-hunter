#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_frankdevhub_51job_client.h"

class frankdevhub_51job_client : public QMainWindow
{
	Q_OBJECT

public:
	frankdevhub_51job_client(QWidget *parent = Q_NULLPTR);
	
private:
	Ui::frankdevhub_51job_clientClass ui;
};


