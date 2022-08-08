{{/*
Create ncc labels
*/}}
{{- define "ncc.labels" -}}
app.kubernetes.io/name: '{{ .Values.ncc.application }}'
app.kubernetes.io/instance: '{{ .Values.ncc.instance }}'
app.kubernetes.io/version: '{{ .Values.ncc.version }}'
ncc.navercorp.com/installation: '{{ .Values.ncc.installation }}'
{{- end -}}

{{/*
Create ncc selectors
*/}}
{{- define "ncc.selectors" -}}
app.kubernetes.io/name: '{{ .Values.ncc.application }}'
app.kubernetes.io/instance: '{{ .Values.ncc.instance }}'
ncc.navercorp.com/installation: '{{ .Values.ncc.installation }}'
{{- end -}}