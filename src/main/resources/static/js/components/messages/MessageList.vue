<template>
    <v-layout align-space-around justify-start column>
        <message-form :messages="messages" :messageAttr="message"/>
        <message-row v-for="message in sortedMessages"
                     :message="message"
                     :messages="messages"
                     :key="message.id"
                     :deleteMessage="deleteMessage"
                     :editMessage="editMessage"/>
    </v-layout>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    export default {
        props: ['messages'],
        components: {
            MessageRow,
            MessageForm
        },
        computed: {
            sortedMessages(){
                return this.messages.sort((a, b) => -(a.id - b.id))
            }
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message){
                this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                    if(result.ok){
                        this.messages.splice(this.messages.indexOf(message), 1)
                    }
                })
            }
        }
    }
</script>

<style></style>